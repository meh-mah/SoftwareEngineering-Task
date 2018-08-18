# Software Engineering-Task

This task is intended to test candidates' ability to consume a webpage, process some data and present it.

Requirements
------------

Using best practice coding methods, build a console application that scrapes the Sainsbury’s grocery site - Ripe Fruits page and returns a JSON array of all the products on the page.

The link to use is:
http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518&top_category=12518&langId=44&beginIndex=0&pageSize=20&catalogId=10137&searchTerm=&categoryId=185749&listId=&storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10137&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0&hideFilters=true

You need to follow each link and get the size (in kb) of the linked HTML (no assets) and the description to display in the JSON
Each element in the JSON results array should contain ‘title’, ‘unit_price’, ‘size’ and ‘description’ keys corresponding to items in the table.

Additionally, there should be a total field, which is a sum of all unit prices on the page.

Application Input
-----------------
URL to scrap. If it is not provided as an argument for the main it will use above link as a default URL.
If URL is invalid the application prompt user to enter a valid URL.

Application Output
-----------------
The application generate a JSON format string and display it in the console as well as writing it into 'result.json' file in the root directory of the application. 

Dependencies
------------
- jackson-databind - for reading and writing JSON, either to and from basic POJOs (Plain Old Java Objects), or to and from a general-purpose JSON Tree Model.
- jackson-annotations - for controlling the structure of the JSON file.
- jsoup - for HTML parsing, with DOM, CSS, and jquery-like methods for easy data extraction.
- junit - for unit test.

How to run
-----------------------
There are two ways to run the application:

First way: Clone the repository, and run Maven commands from cmd:
    
    mvn compile
    mvn exec:java

Second way: Generate the JAR file by running following Maven commands in cmd:

    mvn package
    java -jar target/scraper-1.0-SNAPSHOT.jar


How to run tests
-------

Run following Maven command to start unit tests:

    mvn test
