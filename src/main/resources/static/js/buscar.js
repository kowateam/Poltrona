//cargarmas

let productoss=document.querySelectorAll(".searchProduct");
let cargar10=document.querySelector(".cargar10");
let variable55=10;
for(let i=0;i<5;i++){
	productoss[i].classList.remove("hidden3");
}

cargar10.addEventListener("click",function(){
	
	let auxiliar=productoss.length-variable55;

	 if(auxiliar<0){
		for(let i=variable55-5; i<variable55+auxiliar; i++){
			productoss[i].classList.remove("hidden3");
		}
		cargar10.style.display="none";
	}
	else{
		for(let i=variable55-5; i<variable55; i++){
			productoss[i].classList.remove("hidden3");
			
		}
	} 
	
	
	variable55=variable55+5;
	console.log(variable55);
	
})


//precio2
let precio2=document.querySelectorAll(".precio2");

for(let i=0; i<precio2.length;i++){
    //let variable21=parseInt(precio2[i].textContent);
	//precio2[i].innerHTML="$"+variable21;
	var num33 = precio2[i].textContent.replace(/\./g,'');
	if(!isNaN(num33)){
	num33 = num33.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1.');
	num33 = num33.split('').reverse().join('').replace(/^[\.]/,'');
	precio2[i].innerHTML = "$"+num33;
}}


// borde a la categoria 

let c1 =document.querySelector(".c1");
let c2 =document.querySelector(".c2");
let c3 =document.querySelector(".c3");
let c4 =document.querySelector(".c4");
let c5 =document.querySelector(".c5");



let urlString=location.href.toLocaleLowerCase();

let posicion1 = urlString.indexOf("mueble");
let posicion2 = urlString.indexOf("deco");
let posicion3 = urlString.indexOf("ilumi");
let posicion4 = urlString.indexOf("blanco");
let posicion5 = urlString.indexOf("alfo");

if (posicion1 !== -1){
	c1.classList.add("borderCategory");
}
if (posicion2 !== -1){
	c2.classList.add("borderCategory");
}
if (posicion3 !== -1){
	c3.classList.add("borderCategory");
}
if (posicion4 !== -1){
	c4.classList.add("borderCategory");
}
if (posicion5 !== -1){
	c5.classList.add("borderCategory");
}



// borde subcategorias

let sofa1 =document.querySelector(".sofa1");
let sofa2 =document.querySelector(".sofa1 p");
let sillon1 =document.querySelector(".sillon1");
let sillon2 =document.querySelector(".sillon1 p");
let silla1 =document.querySelector(".silla1");
let silla2 =document.querySelector(".silla1 p");
let mesa1 =document.querySelector(".mesa1");
let mesa2 =document.querySelector(".mesa1 p");
let banqueta1 =document.querySelector(".banqueta1");
let banqueta2 =document.querySelector(".banqueta1 p");

let posicion11 = urlString.indexOf("sofa");
let posicion22 = urlString.indexOf("sillon");
let posicion33 = urlString.indexOf("silla");
let posicion44 = urlString.indexOf("mesa");
let posicion55 = urlString.indexOf("banqueta");

if (posicion11 !== -1){
	sofa1.style.backgroundColor="#B48B58";
    sofa2.style.color="white";
}
if (posicion22 !== -1){
	sillon1.style.backgroundColor="#B48B58";
	sillon2.style.color="white";
}
if (posicion33 !== -1){
	silla1.style.backgroundColor="#B48B58";
	silla2.style.color="white";
}
if (posicion44 !== -1){
	mesa1.style.backgroundColor="#B48B58";
	mesa2.style.color="white";
}
if (posicion55 !== -1){
	banqueta1.style.backgroundColor="#B48B58";
	banqueta2.style.color="white";
}