# KetoAIRecipes AI Assistant Instructions

## Project Overview
**KetoAIRecipes** is an Android app combining keto diet recipes with AI-driven personalization. Built with Kotlin, Jetpack Compose, Room database, and microservices (Retrofit API integration).

## Architecture Pattern: Clean Architecture + MVVM

### Layer Structure
- **Model** (`model/`): Data entities (Recipe, MealPlan, Nutrition, User, etc.)
- **Data** (`data/`): Room DAOs, Repositories, Retrofit API service
- **Domain** (`domain/`): Business logic (ViewModels, Engines: RecommendationEngine, MealPlanScheduler, NutritionCalculator)
- **Presentation** (`presentation/`): Jetpack Compose screens and UI components

### Key Architectural Patterns

**Repository Pattern** (e.g., [RecipeRepository](../KetoAIRecipes/app/src/main/java/com/keto/ai/recipes/data/RecipeRepository.kt)):
- Handles network/local data fallback with `withContext(Dispatchers.IO)`
- Manages `loadingState` and `errorState` as MutableLiveData
- Network-first strategy: tries API, falls back to Room cache on failure

**Domain Engines** (specialized business logic):
- **RecommendationEngine**: Implements collaborative + content-based filtering with user interaction history
- **NutritionCalculator**: Object singleton aggregating nutrition across meal components
- **MealPlanScheduler**: Generates weekly plans based on user preferences
- **GroceryListGenerator**: Extracts ingredients from meal plans

**ViewModels** (use MutableLiveData, inherit from ViewModel):
- Orchestrate repositories and engines
- Example: [MealPlanViewModel](../KetoAIRecipes/app/src/main/java/com/keto/ai/recipes/domain/MealPlanViewModel.kt) composes MealPlanScheduler + NutritionCalculator

## Build & Test

**Build System**: Gradle (Kotlin)
- Android compileSdk: 33, minSdk: 24, targetSdk: 33
- Kotlin 1.8.22 with Compose (compiler extension 1.5.0)

**DI Framework**: Hilt 2.48 (use `@HiltViewModel`, `@Provides` in modules)

**Testing Framework**: JUnit + Mockito with `runBlockingTest` for coroutines
- Test file location: `src/test/java/com/keto/ai/recipes/`
- Mock repositories and DAOs; test business logic in isolation

**Build Command** (Android):
```bash
./gradlew build
./gradlew test
./gradlew connectedAndroidTest
```

## Key Dependencies & Patterns

| Library | Purpose | Pattern |
|---------|---------|---------|
| Room 2.5.2 | Local database | @Entity models, suspend functions in DAOs |
| Retrofit 2.9.0 | HTTP client | Retrofit interfaces, Dispatcher.IO coroutines |
| Coroutines | Async | withContext(Dispatchers.IO) for DB/network calls |
| Compose | UI | Composable functions in `presentation/` folder |
| Hilt 2.48 | Dependency injection | Constructor injection for repos, engines |

## Code Conventions

1. **Coroutine scoping**: Suspend functions with `withContext(Dispatchers.IO)` for blocking ops
2. **Data binding**: MutableLiveData for UI state; observe in Composables
3. **Error handling**: Repos catch exceptions and fall back to cache or return null
4. **Naming**: ViewModel classes end with "ViewModel" (e.g., MealPlanViewModel, UserPreferencesViewModel)
5. **Entity design**: Use `@PrimaryKey(autoGenerate = true)` for auto-incrementing IDs

## Integration Points

- **Recipe API**: [RecipeApiService](../KetoAIRecipes/app/src/main/java/com/keto/ai/recipes/data/RecipeApiService.kt) provides `getRecipes()`, `searchRecipes()`, `getRecipeById()`
- **Recommendation system**: Hooks into [UserInteractionStore](../KetoAIRecipes/app/src/main/java/com/keto/ai/recipes/domain/RecommendationEngine.kt) interface for analytics
- **Nutrition tracking**: [NutritionDao](../KetoAIRecipes/app/src/main/java/com/keto/ai/recipes/data/NutritionDao.kt) joins recipe-to-nutrition mappings
- **Multi-user support**: [UserDao](../KetoAIRecipes/app/src/main/java/com/keto/ai/recipes/data/UserDao.kt) + userId-based preference isolation in RecommendationEngine

## Common Tasks

**Adding a new feature**:
1. Create data model in `model/`
2. Add DAO in `data/` (if persistence needed)
3. Add business logic in `domain/` (ViewModel or Engine)
4. Create Composables in `presentation/`

**Adding a new API endpoint**:
1. Add method to [RecipeApiService](../KetoAIRecipes/app/src/main/java/com/keto/ai/recipes/data/RecipeApiService.kt)
2. Update relevant Repository with try/catch fallback
3. Add tests in `src/test/`

