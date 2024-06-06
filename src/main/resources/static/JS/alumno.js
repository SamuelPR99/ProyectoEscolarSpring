function toggleDetails(element) {
    var details = element.nextElementSibling;
    if (details.style.maxHeight === "0px" || details.style.maxHeight === "") {
        details.style.display = "block";
        setTimeout(function() {
            details.style.opacity = "1";
            details.style.maxHeight = "500px";
            details.style.visibility = "visible";
        }, 10);
    } else {
        details.style.opacity = "0";
        details.style.maxHeight = "0";
        details.style.visibility = "hidden";
        setTimeout(function() {
            if (details.style.opacity === "0") {
                details.style.display = "none";
            }
        }, 500);
    }
}

document.addEventListener("DOMContentLoaded", function() {
    var detalles = document.querySelectorAll('.tarea > div:nth-child(2)');
    detalles.forEach(function(detalle) {
        detalle.style.maxHeight = "0px";
    });
});