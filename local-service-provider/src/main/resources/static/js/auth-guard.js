(function () {
    const token = localStorage.getItem('token');
    const role = localStorage.getItem('role');
    const path = window.location.pathname;

    if (!token) {
        window.location.href = '/pages/login.html';
        return;
    }

    if (path.includes('admin-dashboard') && role !== 'ADMIN') {
        window.location.href = '/';
    }
    if (path.includes('provider-dashboard') && role !== 'PROVIDER') {
        window.location.href = '/';
    }
    if (path.includes('user-dashboard') && role !== 'USER') {
        window.location.href = '/';
    }
})();
