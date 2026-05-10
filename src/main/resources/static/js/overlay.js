const overlay = document.getElementById("overlay");
const btn = document.getElementById("toggle");

btn.addEventListener("click", () => {
  overlay.classList.toggle("is-hidden");
});

// Si quieres que se cierre al hacer click en el overlay:
// overlay.addEventListener("click", () => overlay.classList.add("is-hidden"));
