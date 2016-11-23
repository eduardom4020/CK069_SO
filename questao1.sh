#!/bin/bash
if [ $# -gt 0 ]  
then
	echo "Abriu o arquivo " $file

#associamos o filedescriptor 10 para ser stdin
	exec 10<&0
#atribuimos o nome do arquivo ao comando exec
	exec < $1
	
	while read LINE; do
		echo $LINE
		echo 
		echo
	done
#devolve a entrada padrao para o teclado e fecha o stdin
	exec 0<&10 10<&-
	
else
	echo "Entre com o nome do arquivo como parametro"
fi
