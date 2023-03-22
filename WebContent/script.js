
		function nascondiMessaggio(){
			document.getElementById("messaggioConferma").style.display = "none";
		}
		
		function showError(idError){
	        document.getElementById(idError).style.display = "inline-block";    
	    }

	    function hideError(idError){
	        document.getElementById(idError).style.display = "none";
	    }

	    // controlla che la password rispetti gli standard definiti dall'espressione regolare
	    function validatePass(password){
	        return !!password.match(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%* #+=\(\)\^?&])[A-Za-z\d$@$!%* #+=\(\)\^?&]{3,}$/);
	    };
	    
	    // controlla che la mail contenga la @
	    // se il controllo va a buon fine fa il submit della form
	    function controlloCampiMail(){
	    	let mail = document.getElementById("newmail").value; 
	    	if(mail == "" || mail.indexOf("@")==-1 ){
	            showError("errorMail");
	        }else{
	        	document.getElementById('mailForm').submit();
	        }
	    }
	    
	    // setta il voto della recensione e colora di conseguenza le stelle
	    function Valutazione(id){
			document.getElementById("valutazione").value = id ; 
			for(let i = id; i > 0; i--){
				document.getElementById(i+'star').src = "images/starPiena.png";
			}
			for(let i = id+1; i < 6; i++){
				document.getElementById(i+'star').src = "images/starVuota.png";
			}
			// il bottone è cliccabile quindi si può fare la recensione
			document.getElementById("bottFaiRec").disabled = false;
		}
	    
	    
	    // jquery: serve per far scendere il form di inserimento indriizzo e carta
	    $(document).ready(function(){
    	  $("#addIndirizzo").click(function(){
    	    $("#formIndirizzo").slideToggle(400);
    	  });
    	});
	    
	    $(document).ready(function(){
    	  $("#addCarta").click(function(){
    	    $("#formCarta").slideToggle(400);
    	  });
    	});
	    
	    
	    // controllo sulla nuova password e vecchia password
	    function controlloCampiPass(){
	    	let pass = document.getElementById("oldpss").value;
	    	let pass2 = document.getElementById("newpss").value;
	    	if(!validatePass(pass2) || pass=='' || pass2==''){
	            showError("errorPass");
	            hideError("errorPass2");
	        }
	        else{
	            hideError("errorPass");
	            hideError("errorPass2");
	            document.getElementById('passForm').submit();
	        }
	    }

	    // funzione per validare il form di registrazione
	    function validate(){
	        let mail = document.getElementById("email").value; 
	        let pass = document.getElementById("password").value;
	        let pass2 = document.getElementById("password2").value;

	        // controllo sulla email
	        if(mail == "" || mail.indexOf("@")==-1 ){
	            showError("errorMail");
	        }else{
	            hideError("errorMail");
	        }

	        // controllo sulla password
	        if(pass == ""){
	            showError("errorPass");
	        }else if(pass2 != pass){
	            hideError("errorPass");
	            showError("errorPass2"); 
	        }else if(!validatePass(pass)){
	            showError("errorPass");
	        }
	        else{
	            hideError("errorPass");
	            hideError("errorPass2");
	            if(allLetter()){
	            	document.getElementById("form").submit();
	            }
	        }
	    }
	    
	    // controlla che  il nome e il cognome siano composti da sole lettere 
	    function allLetter()
	    {
	     let letters = /^[a-zA-Z\s]*$/;
	     let cognome = document.getElementById('cognome').value;
	     let nome = document.getElementById('nome').value;
	     if(nome.match(letters) && cognome.match(letters) && nome != "" && cognome != "")
	       {
	    	 	document.getElementById('errorNominativo').style.display = 'none'; 
		    	return true;
	       }
	     else
	       {
		       document.getElementById('errorNominativo').style.display = 'block';
		       return false;
	       }
	    }
	    
	    // stampa il prodotto nel div della ricerca
	    function stampaProdottoRicerca(prodotto, prodotti, i){
	    	prodotto = prodotti[i].split('#');
	    	let uri = "prodotto.jsp?codiceprodotto="+prodotto[3]+"&gusto="+prodotto[1]+"&mole="+prodotto[2]+"";
			document.getElementById('divProdottiDellaRicerca').innerHTML += "<a href='"+uri+"'>"+prodotto[0]+"</a>" + "<br><br>";
	    }
	    
	    function controlloCampiLogin(){
	    	if(document.getElementById('mail').value != "" && document.getElementById('password').value != "")
	    			document.getElementById('form').submit();
	    	else
	    		document.getElementById('error1').style.display = "block";
	    }
	    
	    // quando scrivo una lettera nella barra di ricerca (keyup)
	    // viene eseguita la function che sta tra parentesti
	    $(document).ready(function(){
	    	$("#textboxRicerca").keyup(function(){
	    		let nome = document.getElementById('textboxRicerca').value;  // prendo quello che ho scritto
	    		let prodotto;
	    		// lo passo in get alla servlet ricerca
	    		$.get('Ricerca', {'nome': nome}, function(resp){
	    			if(document.getElementById('textboxRicerca').value != ''){
	    				document.getElementById('divProdottiDellaRicerca').innerHTML = "";
		    			let prodotti = resp.split(';');
		    			document.getElementById('divProdottiDellaRicerca').style.display = "block";
		    			if(prodotti.length > 4){
		    				for(var i=0; i<4;i++){
		    					stampaProdottoRicerca(prodotto, prodotti, i);
			    			}
		    			}else if(prodotti.length == 1){
		    				document.getElementById('divProdottiDellaRicerca').innerHTML = "Nessun prodotto corrisponde ai criteri di ricerca";
		    			}else{
		    				for(var i=0; i<prodotti.length;i++){		    					
		    					stampaProdottoRicerca(prodotto, prodotti, i);
			    			}
		    			}
	    			}else{
	    				document.getElementById('divProdottiDellaRicerca').innerHTML = "";
	    				document.getElementById('divProdottiDellaRicerca').style.display = "none";
	    			}
	    		});
	    	});
	    });
	    
	    
	    // se cambio il gusto o la mole chiamo la servlet cambiaPrezzo che mi restituirà  
	    // il prezzo corrispondente alla combinazione selezionata
	    $(document).ready(function(){
	    	$("#idGusto, #idMole").change(function(event){
	    		let id = document.getElementById('idProdotto').value;
		    	let gusto = document.getElementById('idGusto').value;
		    	let mole = document.getElementById('idMole').value;
		    	$.get('cambiaPrezzo', {"codProd": id, "codGusto": gusto, "codGrammatura": mole}, function(resp){
		    		if(resp.Prezzo_Scontato == 0.0){
		    			document.getElementById('prezzoProd').innerHTML = resp.prezzo + " $";
		    		}else{
		    			document.getElementById('prezzoProd').innerHTML = resp.Prezzo_Scontato + " $";
	    				document.getElementById('prezzoBarrato').innerHTML = resp.prezzo+ " $";
		    		}
		    	});
	    	});
	    });
	    
	    // cambia quantità, prezzo e totale nel carrello
	    function CambiaQuantita(value,prod,mole,gusto,id,prezzoSingolo,PrezzoSingoloScontato,totale){
	    	let prezzo ; 
	    	
	    	if(PrezzoSingoloScontato == 0.0)
	    		prezzo = prezzoSingolo ; 
	    	else
	    		prezzo = PrezzoSingoloScontato ; 
	    	
	    	// decremento
	    	if(value == -1){
	    		$.get('DecrementaQuantitaCarrello',{"ID_prod" : prod , "ID_mole" : mole , "ID_gusto" : gusto },function(resp){
	    			document.getElementById('QuantitaCarrelloProdotto'+id).innerHTML = parseInt(document.getElementById('QuantitaCarrelloProdotto'+id).innerHTML) - 1; 
	    			document.getElementById('Prezzo'+id).innerHTML = (parseFloat(document.getElementById('Prezzo'+id).innerHTML) - prezzo).toFixed(2) + " $" ;
	    			document.getElementById('totale').innerHTML = resp.toFixed(2);
	    			if(document.getElementById('QuantitaCarrelloProdotto'+id).innerHTML == "0"){
	    				location.reload();
	    			}
	    		})
	    	}
	    	// incremento
	    	else{
	    		$.get('IncrementaQuantitaCarrello',{"ID_prod" : prod , "ID_mole" : mole , "ID_gusto" : gusto },function(resp){
	    			document.getElementById('QuantitaCarrelloProdotto'+id).innerHTML = parseInt(document.getElementById('QuantitaCarrelloProdotto'+id).innerHTML) + 1 ; 
	    			document.getElementById('Prezzo'+id).innerHTML = (parseFloat(document.getElementById('Prezzo'+id).innerHTML) + prezzo).toFixed(2) + " $" ;
	    			document.getElementById('totale').innerHTML = resp.toFixed(2);
	    		})
	    	}
	    }
	    
	    // mostra il menu del telefono
	    function mostraMenu(){
			document.getElementById("menuTelefono").style.display = "block";
			document.body.style.overflow = "hidden";
		}
		
	    // nasconde il menu del telefono
		function nascondiMenu(){
			document.getElementById("menuTelefono").style.display = "none";
			document.body.style.overflow = "auto";
		}
		
		// mostra sottocategoria del telefono
		function mostraSottoCat(cat){
			document.getElementById(cat).style.display = "block";
		}
		
		// serve per scegliere il metodo di pagametno e l'indirizzo
		function SelectRadio(id,value){
			document.getElementById(id).value = document.getElementById(id+value).value ; 
		}
		
		// controlla se sono stati selezionati gli input nel riepilogo, altrimenti non puoi andare avanti
		function RadioChecker(id1,id2,id3){
			if(document.getElementById(id1).value == "" || document.getElementById(id2).value == "" || document.getElementById(id3).value == ""){
				alert("Devi selezionare Un indirizzo di spedizione, un metodo di pagamento per andare al checkout e inserire una mail") ;
				document.location.reload(true) ;
			}else
				document.getElementById("formCheck").submit() ; 
		}
		
		function Email(id,id2){
			document.getElementById(id).value = document.getElementById(id2).value ; 
		}
		
		// espressione regolare numero carta
		function cardnumber()
		{
		  var cardno = /^([0-9]{16})$/;
		  if(document.getElementById('numcarta').value.match(cardno)){
		      return true;
		  }
	      else{
	        alert("Carta di credito non valida");
	        return false;
	      }
		}
		
		
		//espressione regolare cvv
		function cvv(){
			var cardno = /^([0-9]{3})$/;
			  if(document.getElementById('numcvv').value.match(cardno)){
			      return true;
			  }
		      else{
		        alert("CVV non valido");
		        return false;
		      }
		}
		
		// espressione regolare carta di credito
		function datacreditcard(){
			let cardno = /^(0[1-9]|1[0-2])\/?([0-9]{4}|[0-9]{2})$/;
			  if(document.getElementById('scadenzacarta').value.match(cardno)){
			      return true;
			  }
		      else{
		        alert("Data non valida");
		        return false;
		      }
		}
		
		// cambia il bordo dell'indirizzo selezionato
		function cambiaBordo(id){
			let elements = document.getElementsByClassName('singoloSelettoreRiepilogo');
			for(let i = 0; i < elements.length; i++){
				if(elements[i].classList.contains("borderedSelector")){
					elements[i].classList.remove("borderedSelector");
				}
			}
			document.getElementById(id).classList.add("borderedSelector");	
		}
		
		// cambia il bordo della carta selezionata
		function cambiaBordoCarta(id){
			let elements = document.getElementsByClassName('singoloSelettoreRiepilogoCarta');
			for(let i = 0; i < elements.length; i++){
				if(elements[i].classList.contains("borderedSelector")){
					elements[i].classList.remove("borderedSelector");
				}
			}
			document.getElementById(id).classList.add("borderedSelector");	
		}
		
		
		