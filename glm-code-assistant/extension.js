const vscode = require('vscode');
const https = require('https');
const fs = require('fs');
const path = require('path');

function activate(context) {
    // Register the analyze project command
    const analyzeProjectCommand = vscode.commands.registerCommand('glm-code-assistant.analyzeProject', async () => {
        await analyzeProject();
    });

    // Register the explain code command
    const explainCodeCommand = vscode.commands.registerCommand('glm-code-assistant.explainCode', async () => {
        await explainCurrentCode();
    });

    // Register configuration command
    const configureCommand = vscode.commands.registerCommand('glm-code-assistant.configure', async () => {
        await configureApiKey();
    });

    context.subscriptions.push(analyzeProjectCommand, explainCodeCommand, configureCommand);
}

async function configureApiKey() {
    const config = vscode.workspace.getConfiguration('glmAssistant');
    const currentApiKey = config.get('apiKey');
    
    const newApiKey = await vscode.window.showInputBox({
        prompt: 'Enter your OpenRouter API key for GLM access',
        placeHolder: 'sk-or-...',
        value: currentApiKey,
        password: true
    });
    
    if (newApiKey !== undefined) {
        await config.update('apiKey', newApiKey, vscode.ConfigurationTarget.Global);
        vscode.window.showInformationMessage('GLM API key updated successfully');
    }
}

async function analyzeProject() {
    // Check if API key is configured
    const config = vscode.workspace.getConfiguration('glmAssistant');
    const apiKey = config.get('apiKey');
    if (!apiKey) {
        const shouldConfigure = await vscode.window.showErrorMessage(
            'GLM API key not configured. Configure it now?',
            'Configure', 'Cancel'
        );
        
        if (shouldConfigure === 'Configure') {
            await configureApiKey();
        }
        return;
    }

    // Get workspace folder
    const workspaceFolders = vscode.workspace.workspaceFolders;
    if (!workspaceFolders) {
        vscode.window.showErrorMessage('No workspace folder open');
        return;
    }
    const workspacePath = workspaceFolders[0].uri.fsPath;

    // Get user prompt
    const prompt = await vscode.window.showInputBox({
        prompt: 'What would you like to know about your project?',
        placeHolder: 'e.g., Analyze my project structure, Find security issues, etc.'
    });
    if (!prompt) return;

    // Show progress indicator
    await vscode.window.withProgress({
        location: vscode.ProgressLocation.Notification,
        title: "GLM is analyzing your project...",
        cancellable: false
    }, async (progress) => {
        try {
            // Prepare the prompt with context
            const fullPrompt = `
I'm working on a project located at ${workspacePath}.
Here's a list of files in my project:
${await getFileList(workspacePath)}
My question: ${prompt}
Please provide a comprehensive analysis focusing on:
- Architecture patterns
- Potential improvements
- Best practices recommendations
- Any issues you notice
`;
            
            // Call the GLM API
            const response = await callGLMApi(fullPrompt);
            
            // Display the response
            displayResponse(response, 'Project Analysis');
        } catch (error) {
            vscode.window.showErrorMessage(`Error: ${error.message}`);
        }
    });
}

async function explainCurrentCode() {
    // Get active editor
    const editor = vscode.window.activeTextEditor;
    if (!editor) {
        vscode.window.showErrorMessage('No active editor');
        return;
    }
    
    // Get file content
    const document = editor.document;
    const fileContent = document.getText();
    const fileName = document.fileName;
    const languageId = document.languageId;
    
    // Get selected text if any
    const selection = editor.selection;
    const selectedText = selection.isEmpty ? "" : document.getText(selection);
    
    // Get user prompt
    const prompt = await vscode.window.showInputBox({
        prompt: 'What would you like to know about this code?',
        placeHolder: 'e.g., Explain this function, Find bugs, Suggest improvements, etc.'
    });
    if (!prompt) return;
    
    // Check if API key is configured
    const config = vscode.workspace.getConfiguration('glmAssistant');
    const apiKey = config.get('apiKey');
    if (!apiKey) {
        const shouldConfigure = await vscode.window.showErrorMessage(
            'GLM API key not configured. Configure it now?',
            'Configure', 'Cancel'
        );
        
        if (shouldConfigure === 'Configure') {
            await configureApiKey();
        }
        return;
    }
    
    // Show progress indicator
    await vscode.window.withProgress({
        location: vscode.ProgressLocation.Notification,
        title: "GLM is analyzing your code...",
        cancellable: false
    }, async (progress) => {
        try {
            // Apply file size limitation
            const maxFileSize = config.get('maxFileSize', 50000);
            let effectiveFileContent = fileContent;
            if (fileContent.length > maxFileSize) {
                effectiveFileContent = fileContent.substring(0, maxFileSize) + '... [truncated]';
            }
            
            // Prepare the prompt with context
            const fullPrompt = `
I'm working on a ${languageId} file named ${fileName}.
${selectedText ? `Here's the specific code I'm focusing on:\n\`\`\`${languageId}\n${selectedText}\n\`\`\`\n\n` : ''}
Here's the full file content for context:\n\`\`\`${languageId}\n${effectiveFileContent}\n\`\`\`
My question: ${prompt}
Please provide a detailed explanation focusing on:
- What the code does
- How it works
- Potential improvements
- Best practices
- Any issues you notice
`;
            
            // Call the GLM API
            const response = await callGLMApi(fullPrompt);
            
            // Display the response
            displayResponse(response, 'Code Explanation');
        } catch (error) {
            vscode.window.showErrorMessage(`Error: ${error.message}`);
        }
    });
}

async function getFileList(dirPath) {
    return new Promise((resolve) => {
        let fileList = '';
        function readDirectory(currentPath, indent = 0) {
            try {
                const files = fs.readdirSync(currentPath);
                for (const file of files) {
                    const filePath = path.join(currentPath, file);
                    let stat;
                    try {
                        stat = fs.statSync(filePath);
                    } catch (e) {
                        // Skip files/directories that can't be accessed
                        continue;
                    }
                    if (stat.isDirectory()) {
                        // Skip common directories to avoid clutter
                        if (!['node_modules', '.git', 'dist', 'out', 'build', '.vscode'].includes(file)) {
                            fileList += '  '.repeat(indent) + `📁 ${file}/\n`;
                            readDirectory(filePath, indent + 1);
                        }
                    } else {
                        const extension = path.extname(file);
                        let icon = '📄';
                        if (['.js', '.ts', '.jsx', '.tsx'].includes(extension)) icon = '📜';
                        else if (['.md', '.txt'].includes(extension)) icon = '📝';
                        else if (['.json', '.yaml', '.yml'].includes(extension)) icon = '🔧';
                        else if (['.py', '.java', '.cpp', '.c', '.go', '.rs'].includes(extension)) icon = '📋';
                        
                        fileList += '  '.repeat(indent) + `${icon} ${file}\n`;
                    }
                }
            } catch (e) {
                // Skip directories that can't be accessed
                fileList += '  '.repeat(indent) + `📁 [Access Denied]\n`;
            }
        }
        readDirectory(dirPath);
        resolve(fileList);
    });
}

function callGLMApi(prompt) {
    return new Promise((resolve, reject) => {
        const config = vscode.workspace.getConfiguration('glmAssistant');
        const model = config.get('model', 'z-ai/glm-4.5-air:free');
        const apiKey = config.get('apiKey');
        
        if (!apiKey) {
            reject(new Error('API key not configured'));
            return;
        }
        
        const postData = JSON.stringify({
            model: model,
            messages: [
                { role: 'user', content: prompt }
            ]
        });
        
        const options = {
            hostname: 'openrouter.ai',
            port: 443,
            path: '/api/v1/chat/completions',
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${apiKey}`,
                'Content-Type': 'application/json',
                'Content-Length': Buffer.byteLength(postData),
                'HTTP-Referer': 'https://localhost',
                'X-Title': 'GLM Code Assistant'
            }
        };
        
        const req = https.request(options, (res) => {
            let data = '';
            res.on('data', (chunk) => {
                data += chunk;
            });
            res.on('end', () => {
                try {
                    const response = JSON.parse(data);
                    if (response.error) {
                        reject(new Error(response.error.message || 'Unknown API error'));
                    } else {
                        resolve(response.choices[0].message.content);
                    }
                } catch (e) {
                    reject(new Error(`Failed to parse API response: ${e.message}`));
                }
            });
        });
        
        req.on('error', (error) => {
            reject(new Error(`Request error: ${error.message}`));
        });
        
        req.setTimeout(30000); // 30 second timeout
        req.write(postData);
        req.end();
    });
}

function displayResponse(content, title) {
    // Create a new document with the response
    const document = vscode.workspace.openTextDocument({
        content: `# ${title}\n\n${content}`,
        language: 'markdown'
    });
    
    // Show the document in a new editor tab
    vscode.window.showTextDocument(document, { preview: false });
}

function deactivate() {}

module.exports = {
    activate,
    deactivate
};