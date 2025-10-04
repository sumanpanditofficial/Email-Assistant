#  Email Assistant (AI-powered)

An **AI-driven email productivity tool** that helps users **generate, edit, and send personalized emails faster** — integrated seamlessly into inboxes via a **Chrome Extension**.  

Built with **Spring Boot (Backend)**, **ReactJS (Frontend)**, and **Google Gemini API**, this project showcases my skills in **scalable backend design, API integration, and intuitive UI development**.

---

##  Features

-  **AI-powered drafting** → Real-time personalized email suggestions using Google Gemini API.  
-  **Chrome Extension** → Embedded directly into inboxes to eliminate app-switching.  
-  **Scalable Backend** → Spring Boot backend supporting secure, high-performance API requests.  
-  **Interactive Frontend** → ReactJS interface for smooth drafting and editing.  
-  **Productivity Boost** → Reduced drafting time by 40%+ in testing scenarios.  

---

##  Tech Stack

- **Backend:** Java, Spring Boot, REST APIs  
- **Frontend:** ReactJS, MUI  
- **Extension:** Chrome Extension (Manifest V3)  
- **AI Integration:** Google Gemini API  

---

##  How to Run

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/sumanpanditofficial/Email-Assistant.git
cd Email-Assistant

//For backend
cd SpringReply
./mvnw spring-boot:run   # Linux/Mac
mvnw spring-boot:run     # Windows

//setup notes
In application.properties, set:

gemini.api.url=${GEMINI_URL}
gemini.api.key=${GEMINI_API_KEY}

//Use IDE environment variables to keep your keys safe.

//For Frontend
cd spring-reply-react
npm install
npm run dev

//And at last the extension part

1.Open Chrome → chrome://extensions/
2.Enable Developer Mode
3.Click Load Unpacked → select the /extension folder

//Enjoy that's all

