export const Todos = {
    findFor: async (userId) => {
        const response = await fetch(`api/todos?userId=${userId}`);
        return response.json();
    },

    create: async (userId, contents) => {
        const todo = {
            userId: userId,
            contents: contents
        };

        const response = await fetch(`api/todos`, {
            method: 'POST',
            mode: 'cors',
            cache: 'no-cache',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            redirect: 'follow',
            body: JSON.stringify(todo)
        });

        return response.json();
    }
}