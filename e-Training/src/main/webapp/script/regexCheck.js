function emailCheck(campo) {
	var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return error(campo,regex.test(campo.val()));
}

function passwdCheck(campo) {
	var regex = /^\S{6,20}$/;
	return error(campo,regex.test(campo.val()));
}

function aziendaCheck(campo) {
	var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return error(campo,regex.test(campo.val()));
}

function offertaFormativaCheck(campo) {
	var regex = /^(?=\S)(?=[a-zA-Z0-9òàèù' ])([a-zA-Z0-9òàèù' ]{0,249})$/;
	return error(campo,regex.test(campo.val()));
}

function cfuCheck(campo) {
	var regex = /^(6|12)$/;
	return error(campo,regex.test(campo.val()));
}

function tipoCheck(campo) {
	var regex = /^(tutorDidattico|tutorAziendale|funzionario|azienda|tirocinante)$/;
	return error(campo,regex.test(campo.val()));
}

//attività svolta
//obbiettivi tirocinio
//facilitazioni
function testoCheck(campo) {
	if (campo == undefined) 
		return true;
	
	var regex = /^(?=\S)(?=[a-zA-Z0-9òàèù' ])([a-zA-Z0-9òàèù' ]{4,49})$/;
	return error(campo,regex.test(campo.val()));
}

function motivazioniRifiutoCheck(campo) {
	var regex = /^(?=\S)(?=[a-zA-Z0-9òàèù' ])([a-zA-Z0-9òàèù' ]{0,499})$/;
	return error(campo,regex.test(campo.val()));
}

function temaCheck(campo) {
	var regex = /^(?=\S)(?=[a-zA-Z0-9òàèù' ])([a-zA-Z0-9òàèù' ]{4,249})$/;
	return error(campo,regex.test(campo.val()));
}

function tutorCheck(campo) {
	var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return error(campo,regex.test(campo.val()));
}

function nomeCheck(campo) {
	var regex = /^[a-zA-Zòàèù ]{2,50}$/;
	return error(campo,regex.test(campo.val()));
}

function cognomeCheck(campo) {
	var regex = /^[a-zA-Zòàèù' ]{2,50}$/;
	return error(campo,regex.test(campo.val()));
}

function annoCheck(campo) {
	var regex = /^[0-9]{4}\/[0-9]{4}$/;
	return error(campo,regex.test(campo.val()));
}

function matricolaCheck(campo) {
	var regex = /^(05121){1}[0-9]{5}$/;	
	return error(campo,regex.test(campo.val()));
}

function emailIstituzionaleCheck(campo) {
	var regex = /^[a-z].[a-z]+[0-9]*@studenti.unisa.it$/;
	return error(campo,regex.test(campo.val()));
}

function oreCheck(campo) {
	var regex = /^\d{1,2}$/;
	return error(campo,regex.test(campo.val()));
}

function dataCheck(campo) {
	var regex = /^(?:\d{4})-(?:(?:(09-|04-|06-|11-)(?:01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30))|(?:(?:01-|03-|05-|07-|08-|10-|12-)(?:01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31))|(?:02-(?:01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29)))$/;
	
	var dataFine = $("[name='dataFine']");
	if (campo != dataFine) {
		dataFine.attr("min",campo.val());
	}
		
	return error(campo,regex.test(campo.val()));
}

function error(campo, risultato) {
	if(risultato){
		campo.removeAttr("data-toggle");
		campo.popover("hide");
	}
	else{
		campo.attr("data-toggle","popover");
		campo.popover("show");
	}
	
	return risultato;
}

function checkCampiLogin() {
	var email = $("[name='inputEmail']");
	var passwd = $("[name='inputPassword']");
	var tipo = $("[name='inputTipo']");
	
	var ch1 = emailCheck(email);
	var ch2 = passwdCheck(passwd);
	var ch3 = tipoCheck(tipo);
	
	return (ch1 && ch2 && ch3);
}

function checkCampiRichiestaTirocinio() {
	var azienda = $("[name='azienda']");
	var offerta = $("[name='offertaFormativa']");
	var cfu = $("[name='cfu']");
	var label0 = $("[name='label0']");
	var label1 = $("[name='label1']");
	var label2 = $("[name='label2']");
	var label3 = $("[name='label3']");
	var label4 = $("[name='label4']");
	
	if (label0.val() == "")
		return error(label0,false);
	
	var ch1 = aziendaCheck(azienda);
	var ch2 = offertaFormativaCheck(offerta);
	var ch3 = cfuCheck(cfu);
	var ch4 = testoCheck(label0);
	var ch5 = testoCheck(label1);
	var ch6 = testoCheck(label2);
	var ch7 = testoCheck(label3);
	var ch8 = testoCheck(label4);

	return (ch1 && ch2 && ch3 && ch4 && ch5 && ch6 && ch7 && ch8);
}

function checkCampiAggiungiOfferta() {
	var tema = $("[name='tema']");
	var dataInizio = $("[name='dataInizio']");
	var dataFine = $("[name='dataFine']");
	var tutorAcc = $("[name='tutorAccademico']");
	var tutorDid = $("[name='tutorAziendale']");
	var label0 = $("[name='label0']");
	var label1 = $("[name='label1']");
	var label2 = $("[name='label2']");
	var label3 = $("[name='label3']");
	var label4 = $("[name='label4']");
	
	var dataInizioDate = new Date(dataInizio);
	var dataFineDate = new Date(dataFine);
	
	if (dataInizioDate > dataFineDate) {
		return error(dataFineDate,false);
	}
	
	var ch1 = temaCheck(tema);
	var ch2 = dataCheck(dataInizio);
	var ch3 = dataCheck(dataFine);
	var ch4 = tutorCheck(tutorAcc);
	var ch5 = tutorCheck(tutorDid);
	
	var ch6;
	if (label0.val() == "")
		ch6 = true;
	else 
		ch6 = testoCheck(label0);
	
	var ch7 = testoCheck(label1);
	var ch8 = testoCheck(label2);
	var ch9 = testoCheck(label3);
	var ch10 = testoCheck(label4);
	
	return (ch1 && ch2 && ch3 && ch4 && ch5 && ch6 && ch7 && ch8 && ch9 && ch10);
}

function checkCampiRegistrazione() {
	var nome = $("[name='nome']");
	var cognome = $("[name='cognome']");
	var email = $("[name='email']");
	var matricola = $("[name='matricola']");
	var anno = $("[name='anno']");
	var password = $("[name='password']");
	var password2 = $("[name='password2']");

	var ch1 = nomeCheck(nome);
	var ch2 = cognomeCheck(cognome);
	var ch3 = emailIstituzionaleCheck(email);
	var ch4 = matricolaCheck(matricola);
	var ch5 = annoCheck(anno);
	var ch6 = passwdCheck(password);
	var ch7 = passwdEquals(password2);
	
	return (ch1 && ch2 && ch3 && ch4 && ch5 && ch6 && ch7);
}

function passwdEquals(campo) {
	var passwd = $("#password").val();
	error(campo, passwd == campo.val());
}

function checkCampiAggiungiAttivita() {
	var attivita = $("[name='attivita'");
	var dataInizio = $("[name='dataInizio']");
	var dataFine = $("[name='dataFine']");
	var ore = $("[name='ore'");
	
	var dataInizioDate = new Date(dataInizio);
	var dataFineDate = new Date(dataFine);
	
	if (dataInizioDate > dataFineDate) {
		return error(dataFineDate,false);
	}
	
	var ch1 = testoCheck(attivita);
	var ch2 = dataCheck(dataInizio);
	var ch3 = dataCheck(dataFine);
	var ch4 = oreCheck(ore);
	
	return (ch1 && ch2 && ch3 && ch4);
}

function checkCampiConvalida() {
	var motivazioneRifiuto = $("#motivazioneRifiuto");
	
	if (!motivazioniRifiutoCheck(motivazioneRifiuto))
		return;
	else 
		inviaConvalida(motivazioneRifiuto);
}
