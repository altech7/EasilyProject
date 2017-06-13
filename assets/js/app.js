$('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15, // Creates a dropdown of 15 years to control year
    monthsFull: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
    weekdaysShort: ['Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam'],
    weekdaysFull: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
    weekdaysLetter: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
    labelMonthNext: 'Moi prochain',
    labelMonthPrev: 'Moi précédent',
    labelMonthSelect: 'Selectionnez un moi',
    labelYearSelect: 'Selectionnez une année',
    today: "Aujourd'hui",
    clear: 'Effacer',
    close: 'Fermer',
    format: 'dd/mm/yyyy',
    formatSubmit: 'dd/mm/yyyy'
});

$('.parallax').parallax();

function checkFade() {

    var opacity = $('#selectTypeRequirement').css('opacity');

    if (opacity === '0') {
        document.getElementById('selectTypeRequirement').style = 'opacity : 1';
        document.getElementById('inputFonctionnal').value = true;
    } else {
        document.getElementById('selectTypeRequirement').style = 'opacity : 0';
        document.getElementById('inputFonctionnal').value = false;
    }
}

function controlFormTask() {

    var datePicker = document.getElementById('dateStart').value;
    var jalonSelect = document.getElementById('jalonSelect').value;
    if (datePicker || jalonSelect == "") {
        var days = datePicker.substr(0, 2);
        var months = datePicker.substr(3, 2);
        var years = datePicker.substr(6, 4);

        var date = new Date(years, months - 1, days);

        if (date <= new Date()) {
            showDialogError("Veuillez saisir une date de début supérieure à la date du jour.")
        } else if (jalonSelect === "") {
            showDialogError("Veuillez choisir un jalon.")
        } else {
            document.getElementById("taskForm").submit();
        }

    } else {
        showDialogError("Veuillez saisir les champs requis.")
    }
}

function controlFromDeliver() {
    var datePicker = document.getElementById('dateLivraisonReal').value;

    if (datePicker) {
        var days = datePicker.substr(0, 2);
        var months = datePicker.substr(3, 2);
        var years = datePicker.substr(6, 4);

        var date = new Date(years, months - 1, days);

        if (date <= new Date()) {
            showDialogError("Veuillez saisir une date de début supérieure à la date du jour.")
        } else {
            document.getElementById("deliverForm").submit();
        }
    } else {
        showDialogError("Veuillez saisir date.")
    }
}

function showDialogSuccess(msg) {

    swal({
        title: "Succès",
        text: msg,
        type: "success",
        showCancelButton: false,
        confirmButtonColor: "#26a69a",
        closeOnConfirm: false,
        timer: 3000
    });
}

function showDialogError(msg) {

    swal({
        title: "Erreur",
        text: msg,
        type: "warning",
        showCancelButton: false,
        confirmButtonColor: "#DD6B55",
        closeOnConfirm: false,
        timer: 6000
    });
}

function showDialogWithForm(formId) {

    var formHTML = '';

    switch (formId) {

        case "formAddRessource":

            formHTML = getFormAddRessource();
            break;
    }

    showDialogWithContentHTML(formId, formHTML);

}

function showDialogWithContentHTML(formId, contentHTML) {
    swal({
        title: "Ajout d'une ressource",
        text: contentHTML,
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Ajouter",
        cancelButtonText: "Annuler",
        closeOnConfirm: false,
        closeOnCancel: true,
        html: true

    }, function (isConfirm) {
        if (isConfirm) {
            document.getElementById(formId).submit();
        }
    });
}

$('#jalonSelect').on('change', function () {
    var idOfJalon = $('#jalonSelect option:selected').val();

    $.getJSON('/EasilyProject/task/jalon/' + idOfJalon, function (response) {
        var tasks = response;
        $('#previousTask option').remove();
        var options = '';

        options += '<option value="" disabled selected>Choisissez une tâche précédente</option>';
        tasks.forEach(function (item) {
            options += '<option value="' + item.id + '">' + item.label + '</option>';
            $("#previousTask").html(options);
        });
        $('select').material_select();
    });
});

