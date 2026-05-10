  // ===== 2) Cuando termina: SVG se va hacia arriba + aparece navbar text =====
  function endSequence() {
    // A) Animar salida del SVG (sube + fade)
    introLogo.animate(
      [
        { transform: "translateY(0px)", opacity: 1 },
        { transform: "translateY(-60px)", opacity: 0 }
      ],
      { duration: 650, easing: "cubic-bezier(.22,1,.36,1)", fill: "forwards" }
    ).onfinish = () => {
      introLogo.style.display = "none";
    };

    // B) Mostrar el texto del navbar (cae desde arriba)
    // pequeño delay para que se sienta “sincronizado”
    setTimeout(() => {
      navText.classList.add("is-visible");
    }, 120);
  }