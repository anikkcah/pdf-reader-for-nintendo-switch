# NINTENDO SWITCH PDF READER

## Pre-requisites

* make folders 'uploads/pdfs/' in the root for storing the uploaded the pdf files.
* change the 'application-example.properties' file to 'application.properties' and place your own configurations in it.

## Steps to use :

* Run this Java SpringBoot Application in your computer (server).
* type "ipconfig" and get your local IP address.
* as per the application.properties the server runs on port 8080(it is configurable), make sure to check the database connection too using 'MySQL Workbench'.
* Goto Web-browser from your computer, type url+port number and search.
* After the page loads upload your book which you would like to read.
* You can click on the book link to check after uploading.
* In Nintendo Switch you can access the browser using https://www.switchbru.com/dns/
* Now goto your local IP and mention the url with port number and you are done!!
* You can view the books on clicking the name.

* <p><img src = "https://raw.githubusercontent.com/anikkcah/ImageblobsforReadme/master/pdf-reader-for-nintendo-switch.jpg" width="300" height="650" hspace="15"></p>

### Note : The annoying problem is that the Nintendo's web browser can be accessed only for 20 mins, after that it closes and redirects to the Network settings. Again connecting to the network is needed.

credits: uses pdf.js -> https://mozilla.github.io/pdf.js/
