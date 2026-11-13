const overlay = document.getElementById("overlay");
const btn = document.getElementById("toggle");

// Solo conecta el botón si ambos elementos existen en la página
if (btn && overlay) {
  btn.addEventListener("click", () => {
    overlay.classList.toggle("is-hidden");
  });
}

// Si quieres que se cierre al hacer click en el overlay:
// overlay.addEventListener("click", () => overlay.classList.add("is-hidden"));
