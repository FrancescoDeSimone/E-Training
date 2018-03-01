var n = 0;
var scelta;
var idScelta;
var sceltaRichiesta;
var tirocinio;
var convalidaScelta;
var emailTirocinante;
var idOffertaRegistro;
var attivitaSvolta;
var emailTirocinanteRegistro;
var idOffertaRegistroTutte;

function newLabel(stringa){
	
	if (n == 4)
		return;
	
	var d = `<div class="numeroLabel">
		<div class="col-xs-7">
		<input onblur="testoCheck($(this))" data-placement="top" data-content="Errore inserimento testo" 
		style="margin-bottom: 10px" type="text" class="form-control" name="label` + (n+1) +
		`"placeholder="` + stringa + `" maxlength="50">
		</div>`;
	
	$(".bottoni").remove();
	if (n != 3) { 		
		d += `<div class="col-xs-5">
		<button style="margin-bottom: 10px" type="button" class="btn btn-primary bottoni" 
		onclick="newLabel('`+ stringa +`');">+</button>
		</div>
		</div>
		`;
	}
	
	$(d).insertAfter($(".numeroLabel").eq(n));
	
	n++;
}

function ritornaMostraOfferte() {
	 window.location.href = "http://localhost:8080/e-Training/MostraOfferteFormativeEsterneServlet";
}

function ritornaTirociniAttiviStorico() {
	 window.location.href = "http://localhost:8080/e-Training/TirociniAttiviStoricoServlet";
}

function ritornaRegistro() {
	 window.location.href = "http://localhost:8080/e-Training/VisualizzaRegistriTirocinanteServlet";
}

function apriPopup() {
	$(".btn-lg").eq(0).click();
}

function rimuoviTirocinante() {
	 window.location.href = "http://localhost:8080/e-Training/RimuoviTirocinanteServlet";
}

function accettaRifiutaPropostaTirocinioSicuro(s,id) {
	scelta = s;
	idScelta = id;
	
	apriPopup();
}

function accettaRifiutaPropostaTirocinio() {
	window.location.href = "http://localhost:8080/e-Training/ValutareOffertaFormativaTirocinioEsternoServlet" +
			"?scelta=" + scelta + "&id=" + idScelta;
}

function ricercaOfferteFormative(selectInput) {
	var azienda = selectInput.val();
	
	$.ajax({
		  url: "RicercaOfferteFormativeAziendaServlet?azienda=" + azienda,
		  dataType: "json",
		  success: function(data) {
			var offerte = $("#offertaFormativa");
			offerte.empty();

			var i;
		    for (i=0; i< data.length; i++) {
		    	offerte.append($('<option>', {
		            value: data[i].tema,
		            text: data[i].tema,
		        }));
		    }
		    
		    if (i == 0) {
		    	offerte.append($('<option>', {
		            text: "-",
		        }));
		    	
		    	offerte.prop("disabled",true);
		    } else {
			    offerte.prop("disabled",false);
		    }
		    
		    offerte.selectpicker('refresh');
		    
		    ricercaOffertaFormativa($('#offertaFormativa'));
		  }
	});
	
}

function ricercaOffertaFormativa(selectInput) {
	var tema = selectInput.val();
	var azienda = $("#azienda").val();
	
	var div = $(".singolo").eq(0);
	  
    var listaCampi = div.find(".form-inline");
	  
    listaCampi.each(function(i,element) {
    	if (element.getElementsByTagName("span")[0] != undefined)
    		element.removeChild(element.getElementsByTagName("span")[0]);
    });
	
	$.ajax({
		  url: "RicercaOffertaFormativaAziendaServlet?tema=" + tema + "&azienda=" + azienda,
		  dataType: "json",
		  success: function(data) {			  
			  listaCampi.eq(0).append($('<span>', {
		            text: data.tema,
		      }));
			  listaCampi.eq(1).append($('<span>', {
		            text: data.inizio,
		      })); 
			  listaCampi.eq(2).append($('<span>', {
		            text: data.fine,
		      }));
			  listaCampi.eq(3).append($('<span>', {
		            text: data.tutorD,
		      }));
			  listaCampi.eq(4).append($('<span>', {
		            text: data.tutorA,
		      }));
			  
			  
			  var disabilita = false;
			  if (data.facilitazione0 != undefined) {
				  if (data.facilitazione0 == "disabilita") {
					  disabilita = true;
				  } else {
					  var stringaFacilitazioni = data.facilitazione0;
				  }

				  if (data.facilitazione1 != undefined) {
					  if (data.facilitazione1 == "disabilita") {
						  disabilita = true;
					  } else {
						  stringaFacilitazioni += " / " +data.facilitazione1;
					  }
					  
					  if (data.facilitazione2 != undefined) {
						  if (data.facilitazione2 == "disabilita") {
							  disabilita = true;
						  } else {
							  stringaFacilitazioni += " / " +data.facilitazione2;
						  }

						  if (data.facilitazione3 != undefined) {
							  if (data.facilitazione3 == "disabilita") {
								  disabilita = true;
							  } else {
								  stringaFacilitazioni += " / " +data.facilitazione3;
							  }
							  
							  if (data.facilitazione4 != undefined) {
								  if (data.facilitazione4 == "disabilita") {
									  disabilita = true;
								  } else {
									  stringaFacilitazioni += " / " +data.facilitazione4;
								  }
							  }
						  }
					  }
				  }
				  
				  listaCampi.eq(5).append($('<span>', {
			            text: stringaFacilitazioni,
			      }));
			  } else {
				  listaCampi.eq(5).append($('<span>', {
			            text: "-",
			      }));
			  }
			  
			  var div = $("#disabilita");
			  var where = $(".singolo legend").eq(0);
			  var img = `<div id="disabilita" style="position: relative;">
	          		<img style="position: absolute;right: 0px;top: 0px;" class="help" src="img/accessible.png" data-toggle="tooltip" data-placement="bottom" 
	              	title="Questa azienda fornisce agevolazioni per studenti con disabilità motorie">
	              </div>`;

			  if (div.length != 0) {
				  div.remove();
			  }
			  
			  if (disabilita) {
				  where.after(img);
				  $('[data-toggle="tooltip"').tooltip();
			  }
		  }
	});
	
}

function accettaRifiutaRichiestaTirocinioSicuro(s,tirocinante,idOffertaFormativa) {
	sceltaRichiesta = s;
	tirocinio = "&tirocinante=" + tirocinante + "&id=" + idOffertaFormativa;
	
	apriPopup();
}

function accettaRifiutaRichiestaTirocinio() {
	window.location.href = "http://localhost:8080/e-Training/AccettareRifiutareRichiestaTirocinioServlet" +
			"?scelta=" + sceltaRichiesta + tirocinio;
}

function convalidaSicuro(email,id,attivita,scelta) {
	convalidaScelta = scelta;
	emailTirocinante = email;
	idOffertaRegistro = id;
	attivitaSvolta = attivita;
	
	if (scelta == 'convalida')
		$(".btn-lg.sicuroConvalida").eq(0).click();
	else if (scelta == 'nonConvalida')
		$(".btn-lg.sicuroNonConvalida").eq(0).click();
}

function inviaConvalida(motivazioneRifiuto) {
	var aggiungi = "";
	
	if (motivazioneRifiuto != undefined)
		aggiungi = "&motivazione=" + motivazioneRifiuto.val();
	
	window.location.href = "http://localhost:8080/e-Training/ConvalidaAttivitaRegistroServlet" +
	"?scelta=" + convalidaScelta + "&email=" + emailTirocinante + "&id=" + idOffertaRegistro +
	"&attivitaSvolta=" + attivitaSvolta + aggiungi;
}

function valutaBottone(pos) {
	var attivitaNumero = $("#Tirocinio" + pos).find("#AttivitaTirocinio").find(".singolo");
	var convalidaTutteButton = $("#convalidaTutte");
	
	if (attivitaNumero.length == 0) {
		convalidaTutteButton.attr("disabled",true);
	} else {
		emailTirocinanteRegistro = $("#Tirocinio" + pos).find("#emailTirocinante").text();
		idOffertaRegistroTutte = $("#Tirocinio" + pos).find("#idOfferta").text();
			
		convalidaTutteButton.attr("disabled",false);
	}
}

function convalidaTutte() {
	window.location.href = "http://localhost:8080/e-Training/ConvalidaTutteAttivitaRegistroServlet" +
	"?email=" + emailTirocinanteRegistro + "&id=" + idOffertaRegistroTutte;
}

