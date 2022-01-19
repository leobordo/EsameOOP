# project oop

## Wheaterapp

 l'applicazione serve a fornire informazioni dettagliate 
 sul vento (raffica gradi e velocità), e sulla visibilità di 2 città(Roma e Ancona).
 Inoltre è possibile ricevere tali informazioni per un certo periodo di giorni e orario

### INDICE


* [Installazione](#inst)
* [Configurazione](#config)
* [Rotta principale](#rp)
* [Altre rotte](#ar)
* [Documentazione](#doc)
* [Autori](#Autori)


<a name="inst"></a>

## Installazione

il programma è installabile digitando il seguente link e cliccando su "code->download zip":
```
 https://github.com/leobordo/EsameOOP/tree/master  
```
<a name="config"></a>
## Configurazione

Per usare il programma, viene utilizzata una psw apikey che per scopi didattici è già presente nel programma e non ci sarà bisogno di richiederla 
al sito openweather.

<a name="rp"></a>
## Rotta principale
Per usare le rotte su postman occorre usare questo indirizzo:
```
localhost:8080 
```

Per avere informazioni sul vento e visibilità di una città è necessario usare la rotta "/Filters".
Ex:
```
localhost:8080/Filters?
```
Occorre utilizzare i seguenti parametri(KEY):

*cityname* : per inserire il nome della città;

*dayI* : per inserire il primo giorno da cui vogliamo le informazioni;

*period* : nel caso volessimo delle informazioni in uno specifico orario della giornata;

*dayF* : giorno entro il quale vogliamo le informazioni;



Note: 

1) se si inserisce il dayI senza specificare l'orario, verrà stampato i valori dell'intera giornata;

2) al momento  le info riguardano solo Roma o Ancona; 

3) inserire la data come in questo es: 2022/01/01; l'orario come in questo es: 01/04;

4) errori riguardanti lo scorretto inserimento dei dati sono coperti da eccezioni;

![alt text](https://github.com/leobordo/EsameOOP/blob/master/EsameOOP/foto%20readme/1.jpeg)
### Output
esempio di output di un JSONObject che restituisce le informazioni:
![alt text](https://github.com/leobordo/EsameOOP/blob/master/EsameOOP/foto%20readme/2.jpeg)
<a name="ar"></a>
## Altre rotte

le altre rotte che si possono usare a scopo didattico per sperimentare il meccanismo di alcuni importanti metodi del programma
sono le seguenti(come parametro è necessario solo "cityname", e come valore deve avere il nome "Roma", o "Ancona"):

"/General": riporta le info genereali della città;

"/GetWind": riporta le info genereali sul vento della città;

"/GetVisibility": riporta le info genereali sulla visibilità della città;
 
"/SaveHour": salva un documento .txt con all'interno tutte le info su vento e visibilità della città;

Nota: dato che le seguenti rotte sono solo a scopo didattico, non sono state coperte da eccezioni, quindi si prega di inserire correttamente la città "Roma" o "Ancona" per testarne l'uso.


```
localhost:8080/General?cityname=Roma
```
```
localhost:8080/GetWind?cityname=Roma
```
```
localhost:8080/GetVisibility?cityname=Roma
```
```
localhost:8080/SaveHour?cityname=Roma
```


<a name="doc"></a>
### Documentazione

tutta la documentazione è consultabile nella [Javadoc](https://github.com/leobordo/EsameOOP/blob/master/EsameOOP/doc).





<a name="Autori"></a>
### Autori

 Leonardo Bordoni(50%)
 
 Samuele Di Summa(50%)
