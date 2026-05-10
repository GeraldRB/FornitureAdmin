function initIntro() {
  const introLogo = document.getElementById("introLogo");
  const waveGroup = document.getElementById("waveGroup");
  const navText = document.getElementById("navLogoFinal");
  const overlay = document.getElementById("overlay");

  const heroImg = document.querySelector(".zoom-start-big");
  const movingText = document.querySelector(".moving-text");

  if (!introLogo || !waveGroup || !navText || !overlay) {
    console.warn("Faltan elementos:", { introLogo, waveGroup, navText, overlay });
    return;
  }

  const introYaVista = sessionStorage.getItem("introYaVista");

  // Si ya se vio en ESTA pestaña, la saltamos
  if (introYaVista === "true") {
    introLogo.style.display = "none";
    overlay.style.display = "none";
    overlay.classList.add("is-hidden");
    navText.classList.add("is-visible");

    if (heroImg) heroImg.classList.add("active");
    if (movingText) movingText.classList.add("active");

    return;
  }

  navText.classList.remove("is-visible");

  const startY = 4;
  const endY = -2.55;
  const duration = 5200;

  let start = null;

  function animateFill(ts) {
    if (!start) start = ts;
    const t = Math.min((ts - start) / duration, 1);

    const ease = 1 - Math.pow(1 - t, 3);
    const y = startY + (endY - startY) * ease;

    waveGroup.setAttribute("transform", `translate(0 ${y})`);

    if (t < 1) {
      requestAnimationFrame(animateFill);
    } else {
      endSequence();
    }
  }

  function endSequence() {
    introLogo.animate(
      [
        { transform: "translateY(0px)", opacity: 1 },
        { transform: "translateY(-60px)", opacity: 0 }
      ],
      { duration: 650, easing: "cubic-bezier(.22,1,.36,1)", fill: "forwards" }
    ).onfinish = () => {
      introLogo.style.display = "none";
      navText.classList.add("is-visible");
      overlay.classList.add("is-hidden");

      if (heroImg) heroImg.classList.add("active");
      if (movingText) movingText.classList.add("active");

      // Guardar SOLO en esta pestaña
      sessionStorage.setItem("introYaVista", "true");

      setTimeout(() => {
        overlay.style.display = "none";
      }, 600);
    };
  }

  requestAnimationFrame(animateFill);
}

// Carga normal
window.addEventListener("DOMContentLoaded", initIntro);

// Cuando vuelves con atrás/adelante y la página viene de caché
window.addEventListener("pageshow", () => {
  const introYaVista = sessionStorage.getItem("introYaVista");

  if (introYaVista === "true") {
    const introLogo = document.getElementById("introLogo");
    const overlay = document.getElementById("overlay");
    const navText = document.getElementById("navLogoFinal");
    const heroImg = document.querySelector(".zoom-start-big");
    const movingText = document.querySelector(".moving-text");

    if (introLogo) introLogo.style.display = "none";
    if (overlay) {
      overlay.style.display = "none";
      overlay.classList.add("is-hidden");
    }
    if (navText) navText.classList.add("is-visible");
    if (heroImg) heroImg.classList.add("active");
    if (movingText) movingText.classList.add("active");
  }
});