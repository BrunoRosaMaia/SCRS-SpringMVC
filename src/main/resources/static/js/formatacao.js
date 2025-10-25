document.getElementById('cpf').addEventListener('input', function(e){
    let value = e.target.value.replace(/\D/g, '');
    if(value.length > 3 && value.length <= 6)
        value = value.replace(/(\d{3})(\d+)/, '$1.$2');
    else if(value.length > 6 && value.length <= 9)
        value = value.replace(/(\d{3})(\d{3})(\d+)/, '$1.$2.$3');
    else if(value.length > 9)
        value = value.replace(/(\d{3})(\d{3})(\d{3})(\d+)/, '$1.$2.$3-$4');
    e.target.value = value;
});