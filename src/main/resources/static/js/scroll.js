let bloqueado = false;

function isMobile() {
  return window.innerWidth <= 768;
}

const puntos = [
  document.querySelector("#frontPage"),
  document.querySelector("#productGrid"),
  document.querySelector("#TrendyProduct"),
  document.querySelector("#discountGrid"),
  document.querySelector("#featuredGrid"),
  document.querySelector("#decorSection"),
  document.querySelector("#footer")
].filter(Boolean);

function activarAnimacionEnSeccion(seccion) {
  if (!seccion) return;

  let contenedorAnimacion = null;


  if (seccion.id === "productGrid") {
    contenedorAnimacion = document.querySelector(".product-grid");
  }

  if (seccion.id === "TrendyProduct") {
    contenedorAnimacion = document.querySelector(".product-showcase");
  }

  if (seccion.id === "discountGrid") {
    contenedorAnimacion = document.querySelector(".discount-grid");
  }

  if (seccion.id === "featuredGrid") {
    contenedorAnimacion = document.querySelector(".featured-grid");
  }

  if (seccion.id === "decorSection") {
    contenedorAnimacion = document.querySelector(".decor-section");
  }

  if (!contenedorAnimacion) return;

  const imagenes = contenedorAnimacion.querySelectorAll(".card-img-anim");

  imagenes.forEach((img, index) => {
    img.classList.remove("is-visible");
    void img.offsetWidth;

    setTimeout(() => {
      img.classList.add("is-visible");
    }, index * 20);
  });
}

window.addEventListener("wheel", (e) => {
  if (isMobile()) return;

  if (bloqueado) {
    e.preventDefault();
    return;
  }

  const scrollActual = window.scrollY;
  let destino = null;

  if (e.deltaY > 0) {
    destino = puntos.find(p => p.offsetTop > scrollActual + 20);
  } else {
    destino = [...puntos].reverse().find(p => p.offsetTop < scrollActual - 20);
  }

  if (!destino) return;

  e.preventDefault();
  bloqueado = true;

  window.scrollTo({
    top: destino.offsetTop,
    behavior: "smooth"
  });

  setTimeout(() => {
    activarAnimacionEnSeccion(destino);
    bloqueado = false;
  }, 100);
}, { passive: false });