const API = {
    baseUrl: '',

    async get(url) {
        const res = await fetch(this.baseUrl + url);
        if (!res.ok) throw new Error(await res.text());
        return res.json();
    },

    async post(url, data) {
        const res = await fetch(this.baseUrl + url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });
        if (!res.ok) throw new Error(await res.text());
        return res.json();
    },

    async authGet(url) {
        const res = await fetch(this.baseUrl + url, {
            headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
        });
        if (res.status === 401 || res.status === 403) { localStorage.clear(); window.location.href = '/pages/login.html'; }
        if (!res.ok) throw new Error(await res.text());
        return res.json();
    },

    async authPost(url, data) {
        const res = await fetch(this.baseUrl + url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            body: JSON.stringify(data)
        });
        if (res.status === 401 || res.status === 403) { localStorage.clear(); window.location.href = '/pages/login.html'; }
        if (!res.ok) throw new Error(await res.text());
        return res.json();
    },

    async authPut(url, data) {
        const res = await fetch(this.baseUrl + url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            body: JSON.stringify(data)
        });
        if (res.status === 401 || res.status === 403) { localStorage.clear(); window.location.href = '/pages/login.html'; }
        if (!res.ok) throw new Error(await res.text());
        return res.status === 204 ? null : res.json();
    },

    async authDelete(url) {
        const res = await fetch(this.baseUrl + url, {
            method: 'DELETE',
            headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
        });
        if (res.status === 401 || res.status === 403) { localStorage.clear(); window.location.href = '/pages/login.html'; }
        if (!res.ok) throw new Error(await res.text());
        return null;
    }
};
