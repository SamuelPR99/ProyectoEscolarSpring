window.onload = function() {
    var textarea = document.getElementById('descripcionIncidencia');
    var charCount = document.getElementById('charCount');

    textarea.addEventListener('input', function() {
        charCount.textContent = this.value.length + ' / 255 Caracteres';
    });
}

// Obtén los elementos del DOM
var textarea = document.getElementById('descripcionIncidencia');
var charCount = document.getElementById('charCount');

// Función para actualizar el conteo de caracteres
function updateCharCount() {
    var count = textarea.value.length;
    charCount.textContent = count + " / 255 Caracteres";

    // Cambia el color a rojo si se alcanzan los 255 caracteres
    if (count >= 255) {
        charCount.style.color = 'red';
    } else {
        charCount.style.color = 'black';
    }
}

// Agrega el event listener al campo de texto
textarea.addEventListener('input', updateCharCount);

// Actualiza el conteo de caracteres inicialmente
updateCharCount();