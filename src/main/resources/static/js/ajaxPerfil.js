//BORRAR CUENTA
$(document).on("click", "#borrar", function() {
	var div = $(this)[0].parentNode.parentNode.remove();
	var currentRow=$(this).closest("tr");
	var col1=currentRow.find("td:eq(0)").text(); // get account id value

	fetch("/perfil/borrar/" + col1, {
		headers: { "Content-Type": "application/json; charset=utf-8" }
	})
		.then(res => res.json())
		.then(response => {
			if (response) {
				div.remove();
			}
		})
});

//BUSQUEDA RAPIDA

$(document).on("click", "#busqueda", function() {
	var currentRow=$(this).closest("tr");
	var nombre = currentRow.find("td:eq(1)").text(); // get account id value
	sessionStorage.setItem("busquedaRapida", nombre);
	window.location.href = '/index'
});