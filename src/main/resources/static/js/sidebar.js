document.addEventListener("DOMContentLoaded", () => {
    const menuToggle = document.getElementById("menuToggle");
    const sidebar = document.querySelector(".sidebar");

    console.log("menuToggle:", menuToggle);
    console.log("sidebar:", sidebar);

    if (!menuToggle || !sidebar) return;

    menuToggle.addEventListener("click", () => {
        console.log("click");
        sidebar.classList.toggle("active");
    });
});