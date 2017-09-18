
<h1>HTML Analyzer using Jsoup.</h1>

<h2>1-How to run it locally:</h2>
    <h3>Option 1:</h3>
	<ul>
		<li>From the shell, browse to "Springboot-MVC-HtmlAnalyzer/" directory.</li>
		<li>Execute "mvn spring-boot:run" from shell.</li>
		<li>Go to this url to access the home page http://localhost:8086</li>
	</ul>    
    <h3>Option 2:</h3>
	<ul>
		<li>Open netbeans 8 --> File --> Open Project --> browse to source code directory --> Right click --> Run.</li>
		<li>Go to this url to access the home page http://localhost:8086</li>
	</ul>

<h2>2-Development Tools:</h2>
	<ul>
		<li>I used Netbeans 8.1 as development IDE, Maven 3.5, Spring boot 1.5.3, JDK 1.8 and Embedded Apache Tomcat 8.</li>
	</ul>

<h2>3-Technical Design/Decisions:</h2>
    <h3>System design:</h3>
        <ul>
            <li>Used spring boot web as the main framework.</li>
            <li>Used spring dependency injection to inject the beans.</li>
        </ul>
    <h3>Handling URL validation performance issues:</h3>
	<ul>
		<li>Used parallelStream to spread a calculation to threads and the CPU cores available.</li>
		<li>Used ArrayList which performs well on parallel streams.</li>
		<li>Check for duplicate URLs to avoid validating the same URL more than once.</li>
	</ul>    

<h2>4-Testing:</h2>
	<ul>
		<li>Used JUnit, SuperMockito and Mockito for unit testing.</li>
		<li>Created a test case, mocking Jsoup.connect(url).get() and parse html from "html-to-parse.txt" on the test resources.</li>
	</ul>

<h2>5-Deployment:</h2>
	<ul>
		<li>Browse to "Springboot-MVC-HtmlAnalyzer/src/main/docker"</li>
		<li>Run this command from your shell: $ docker build  -t nabil:html-analysis .</li>
		<li>After image built successfully, please execute this command to start the docker container: $ docker run -i -t -p 8086:8086  nabil:html-analysis</li>
		<li>Access this URL from your browser: http://localhost:8086/</li>
	</ul>
        
