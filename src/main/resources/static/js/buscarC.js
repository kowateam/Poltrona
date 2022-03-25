//modal comunicacion 

let modalComunicacion=document.querySelector(".modalComunicacion2");
let modalComunicacion3=document.querySelector(".modalComu3");
let modalComu=document.querySelector(".modalComu");
let oscurecer=document.querySelector(".oscurecer");
modalComunicacion.addEventListener("click",function(){
	modalComu.style.marginBottom="0%";
	oscurecer.classList.remove("hidden3");
})
modalComunicacion3.addEventListener("click",function(){
   modalComu.style.marginBottom="-100%";
   oscurecer.classList.add("hidden3");
})