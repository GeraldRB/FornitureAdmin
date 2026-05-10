function enviarWhatsApp() {
  const nombre = document.querySelector(".product-title").innerText;
  const precio = document.querySelector(".product-price").innerText;

  const numero = "50687876656";

  const mensaje = `Hola, me interesa el mueble ${nombre}.
Precio visto en la web: ${precio}.
¿Podría brindarme más información?`;

  const url = `https://wa.me/${numero}?text=${encodeURIComponent(mensaje)}`;
  window.open(url, "_blank");
}

function changeImage(element) {
  const mainImage = document.getElementById("mainImage");
  mainImage.src = element.src;

  document.querySelectorAll(".product-thumb").forEach(t => {
    t.classList.remove("active");
  });

  element.parentElement.classList.add("active");
}

window.changeImage = changeImage;

document.addEventListener("DOMContentLoaded", () => {
  const btn = document.querySelector(".product-whatsapp-btn");

  if (btn) {
    btn.addEventListener("click", (e) => {
      e.preventDefault();
      enviarWhatsApp();
    });
  }
});