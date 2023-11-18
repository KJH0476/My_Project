document.addEventListener('DOMContentLoaded', function() {
    var menuButton = document.getElementById('menuButton');
    var sidebar = document.getElementById('sidebar');

    if (menuButton && sidebar) {
        menuButton.onclick = function() {
            if (sidebar.style.width === '250px') {
                sidebar.style.width = '0';
            } else {
                sidebar.style.width = '250px';
            }
        };
    }
});