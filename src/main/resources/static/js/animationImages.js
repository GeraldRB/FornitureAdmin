document.addEventListener("DOMContentLoaded", function () {
    const imgs = document.querySelectorAll(".card-img-anim");

    const observer = new IntersectionObserver((entries, obs) => {
        entries.forEach((entry, index) => {
            if (entry.isIntersecting) {

                setTimeout(() => {
                    entry.target.classList.add("is-visible");
                }, index * 120); // efecto stagger

                obs.unobserve(entry.target);
            }
        });
    }, {
        threshold: 0.25
    });

    imgs.forEach(img => observer.observe(img));
});
