var base_url = window.location.origin;

var results = $('.searchProduct').length;
$('.results-counter').text(results);

// Mostrar spinner
$(document).ready(function () {
	setTimeout(function () {
		$('.loading-spinner').addClass('hidden');
	}, 3000);
});

// FORM-CONTROL (INPUTS)
// --------------------------------------------
$('.form-control').focus(function () {
	$(this).closest('.input-group').addClass('focused');
});

$('.form-control').focusout(function () {
	$(this).closest('.input-group').removeClass('focused');
	$(this).closest('.input-group').removeClass('filled');

	if ($(this).val()) {
		$(this).closest('.input-group').addClass('filled');
	}
});

// Mostrar/Ocultar pass
$('.icon-view-show').click(function () {
	$(this).addClass('hidden');
	$(this).closest('.input-group').find('.icon-view-hide').removeClass('hidden');
	$(this).closest('.input-group').find('.form-control').attr('type', 'text');
	$(this).closest('.input-group').find('.form-control').focus();
});

$('.icon-view-hide').click(function () {
	$(this).addClass('hidden');
	$(this).closest('.input-group').find('.icon-view-show').removeClass('hidden');
	$(this).closest('.input-group').find('.form-control').attr('type', 'password');
	$(this).closest('.input-group').find('.form-control').focus();
});

// FORM-CONTROL (RADIO)
// --------------------------------------------
$('.form-option').click(function () {
	$('.form-option').prop('checked', false);
	$(this).prop('checked', true);
});

// OPTION SELECTOR
// --------------------------------------------
$('.option-selector .option').click(function () {

	$(this).closest('.step-container, .option-group').find('.option').removeClass('selected');
	$(this).addClass('selected');

	var validateOption = $(this).closest('.step-container').find('.option-selector input');
	var validatedBtn = $(this).closest('.step-container').find('.btn-step');

	if (validateOption.is(':checked')) {
		validatedBtn.prop('disabled', false);
	} else {
		validatedBtn.prop('disabled', true);
	}
});

// SELECT DROPDOWN
// --------------------------------------------
$('.form-select').change(function () {
	var optionDefault = $(this).find('option').first().val();

	if ($(this).val() == optionDefault) {
		$(this).addClass('default');
	} else {
		$(this).removeClass('default');
	}
});

// Formatear categoria
$('.categoria').each(function () {
	let myStr = $(this).text();
	let firstWord = myStr.split(".")[0];

	$(this).text(firstWord);
});

// Formatear stock
$('.stock').each(function () {
	text = $(this).text();
	text = text.slice(0, -3);

	$(this).text(text);
});

// Formatear el precio
$('.price').each(function () {
	const number = $(this).text();

	var formatedPrice = new Intl.NumberFormat('es-AR', {
		style: 'currency',
		currency: 'ARS',
		minimumFractionDigits: '0',
	}).format(number);

	$(this).text(formatedPrice);
	var text = $(this).text();
	text = text.slice(1, -3);
	$(this).find('.price .ammount').text(text);
});

// Validar fotos (cover)
/*setTimeout(function () {
	var background = $('#coverImage').height();
	var productBgImage = $('#coverImage').prop('src');
	if (background == 0) {
		$('.slide').addClass('hidden');
		$('#slide-empty').removeClass('hidden');
	} else {
		$('#slide').css('background-image', 'url(' + productBgImage + ')');
		$('.slide').removeClass('hidden');
		$('#slide-empty').addClass('hidden');
	}
}, 2500);*/

// Validar QR
setTimeout(function () {
	var background = $('#qrImage').height();
	var qrBgImage = $('#qrImage').prop('src');
	if (background == 0) {

	} else {
		$('.qr-container').css('background-image', 'url(' + qrBgImage + ')');
	}
}, 3600);


$('#buscarProducto').keydown(function () {
	var nombre = $('#buscarProducto').val();

	$.ajax({
		method: "GET",
		url: base_url + "/buscar/" + nombre
	}).done(function (data) {
		if (data == false) {
			// Ocupado
			$('#dirState').removeClass('hidden');
			$('#btnRegEmp2').prop('disabled', true);
		} else {
			// Disponible
			$('#dirState').addClass('hidden');
			$('#btnRegEmp2').prop('disabled', false);
		}
	});
});

// Filtrar los resultados en Buscar
// --------------------------------------------
$("#buscarProducto").on("keyup", function () {
	var value = this.value.toLowerCase().trim();
	$(".result").show().filter(function () {
		return $(this).text().toLowerCase().trim().indexOf(value) == -1;
	}).hide();

	var pepe = $('.result').filter(function() {
	return $(this).css('display') == 'none';
	  }
	).length;

	var results = $('.result').length;
	finalResults = results - pepe;
	$('.results-counter').text(finalResults);

});


// DESCARGAR PDF
// --------------------------------------------
// var doc = new jsPDF();
// var specialElementHandlers = {
//     '.printable': function (element, renderer) {
//         return true;
//     }
// };

// $('#btnDescargar').click(function () {
//     doc.fromHTML($('.printable').html(), 15, 15, {
//         'width': 170,
//             'elementHandlers': specialElementHandlers
//     });
//     doc.save('qr-name.pdf');
// });

// var base_url = window.location.origin;