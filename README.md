# ** Email Assistant (AI-powered)**

-An AI-driven email productivity tool that helps users generate, edit, and send personalized emails faster — integrated seamlessly into inboxes via a Chrome Extension.

 Built with Spring Boot (Backend), ReactJS (Frontend), and Google Gemini API, this project showcases my skills in designing scalable backend services, API integration, and building intuitive user interfaces.

# Features

 -AI-powered drafting → Real-time personalized email suggestions using Google Gemini API.

 -Chrome Extension → Brings the assistant directly into your inbox, eliminating app-switching.

 -Scalable Backend → Spring Boot backend supporting secure, high-performance API requests.

 -Interactive Frontend → ReactJS interface for smooth drafting and editing experience.

 -Productivity Boost → Reduced drafting time by 40%+ in testing scenarios.

 # Tech Stack

Backend: Java, Spring Boot, REST APIs

Frontend: ReactJS, MUI

Extension: Chrome Extension (Manifest V3)

AI Integration: Google Gemini API

``` Clone the repository
git clone https://github.com/sumanpanditofficial/Email-Assistant.git
cd Email-Assistant

# Backend
cd SpringReply, also set your url into the code into the @Value{Gemeni.api.url}, and the api key to @Value("${gemini.api.key}"), in the properties and make sure to use the enviornment variable of your ide so that your key and url is safe.
./mvnw spring-boot:run

# Frontend
cd spring-reply-react
npm install
npm run dev

# Chrome Extension
Load the extension from /extension in Chrome Developer Mode
