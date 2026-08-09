const dropzone = document.getElementById("dropZone");
const dropzoneMsg = document.querySelector("#dropZone p");
const input = document.querySelector("#dropZone input");

const mainPreview = document.getElementById("mainPreview");
const mainInput = document.getElementById("mainImagesInput");

const slots = document.querySelectorAll(".slot-card");

const galleryCount = document.getElementById("galleryCount");
let totalGalleryImages = 0;


const fornitureType = document.getElementById("fornitureType");

const measureWidth = document.querySelector(".measure-width");
const measureHight = document.querySelector(".measure-hight");
const measureDepth = document.querySelector(".measure-depth");
const measureCapacity = document.querySelector(".measure-capacity");
const measureLarge = document.querySelector(".measure-large");
const measureTypeBed = document.querySelector(".measure-type-bed");




window.addEventListener("DOMContentLoaded", () => {
    changeMeasures();

    fornitureType.addEventListener("change", () => {
        changeMeasures();
    });
});


// =========================
// DROPZONE PRINCIPAL
// =========================

dropzone.addEventListener("click", () => {

    input.click();

    input.onchange = (e) => {

        const file = e.target.files[0];

        showMainPreview(file);

    };

});

dropzone.addEventListener("dragover", (e) => {
    e.preventDefault();
});

dropzone.addEventListener("drop", async (e) => {

    e.preventDefault();

    if (e.dataTransfer.items[0].kind !== "file") {
        dropzoneMsg.textContent = "Error: No hay archivo";
        throw new Error("No file");
    }

    if (e.dataTransfer.items.length > 1) {
        dropzoneMsg.textContent = "Error: Solo una imagen";
        throw new Error("Multiple items");
    }

    const filesArray = [...e.dataTransfer.files];

    const isFile = await new Promise((resolve) => {

        const fr = new FileReader();

        fr.onload = () => {
            resolve(true);
        };

        fr.onerror = () => {
            resolve(false);
        };

        fr.readAsArrayBuffer(filesArray[0]);

    });

    if (!isFile) {
        dropzoneMsg.textContent = "Error: No se puede leer";
        throw new Error("Read error");
    }

    // sincronizamos el archivo soltado con el input real (para que viaje en el form)
    const dt = new DataTransfer();
    dt.items.add(filesArray[0]);
    input.files = dt.files;

    showMainPreview(filesArray[0]);

});


// =========================
// GALERIA SECUNDARIA
// =========================

slots.forEach((slot) => {

    const galleryInput = slot.querySelector(".gallery-input");

    slot.addEventListener("click", () => {

        galleryInput.click();

    });

    galleryInput.addEventListener("change", (e) => {

        const file = e.target.files[0];

        if (!file || !file.type.startsWith("image/")) {
            return;
        }

        const reader = new FileReader();

        reader.onload = (event) => {

            let img = slot.querySelector("img");

            if (!img) {
                img = document.createElement("img");
                img.className = "gallery-preview";
                slot.appendChild(img);

                totalGalleryImages++;
                galleryCount.textContent = totalGalleryImages;
            }

            img.src = event.target.result;

        };

        reader.readAsDataURL(file);

    });

});


// =========================
// PREVIEW PRINCIPAL
// =========================

function showMainPreview(file) {

    if (!file || !file.type.startsWith("image/")) {
        return;
    }

    const reader = new FileReader();

    reader.onload = (e) => {

        mainPreview.src = e.target.result;

        mainPreview.style.display = "block";

    };

    reader.readAsDataURL(file);

}

function changeMeasures() {
    const value = fornitureType.value;

    // Mostrar todo primero
    measureCapacity.style.display = "block";
    measureLarge.style.display = "block";
    measureTypeBed.style.display = "block";
    measureHight.style.display = "block";
    measureDepth.style.display = "block";
    measureWidth.style.display = "block";

    // Ocultar según el tipo
    if (value === "1") {
        measureCapacity.style.display = "none";
        measureLarge.style.display = "none";
        measureTypeBed.style.display = "none";
        measureHight.style.display = "none";

    } else if (value === "2") {
        measureCapacity.style.display = "none";
        measureLarge.style.display = "none";
        measureTypeBed.style.display = "none";
        measureHight.style.display = "none";

    } else if (value === "3") {
        measureCapacity.style.display = "none";
        measureLarge.style.display = "none";
        measureDepth.style.display = "none";
        measureHight.style.display = "none";
        measureWidth.style.display = "none";

    } else if (value === "4") {
        measureTypeBed.style.display = "none";
        measureDepth.style.display = "none";
        measureHight.style.display = "none";

    } else if (value === "5") {
        measureTypeBed.style.display = "none";
        measureLarge.style.display = "none";
        measureCapacity.style.display = "none";
    }
}