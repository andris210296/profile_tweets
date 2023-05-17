# profile_tweets
Portfolio Java web app that displays the profile image, name, some text with the experience, and a 5 tweets list of the user’s Twitter timeline.

**Improvements to be done**

There is a bug happening in this project happening in the front-end part when you open the Update Profile Form, for some reason unknown
the layout, colors e design get lost, but the functionality still works.

**Application.properties configuration**

In order to start this application, make sure the database exists and the Twitter oauth codes are presented in the **application.properties** file, the current file already
contains that information, but in the future, the given data can expire. 

Make sure to create a folder inside **\src\main\webapp** with any name desired. This is very important to make the uploaded images to display in the front-end.
In the **application.properties** you can define a name for this folder, but make sure to add manually inside **\src\main\webapp**. If you don't define a folder
the default value would be **photos** without "\" or "/"

**Starting the profile_tweets**

This code was developed using IntelliJ, which can start easily after doing the configurations above.
It's possible to use the front-end or the backend API to manage the portfolios.

# Steps to use the profile_tweets:

#### 1. Using the API

**a)** In order to create a portfolio create a **POST** request at the endpoint **{server}/portfolio** with a body as described below:

Request: {server}/portfolio - POST
```json
{
  "imagePath": "",
  "name": "Jonh Doe",
  "experience": "I have experience working with java",
  "twitterUserName": "MicrosoftBR"
}
```
Response:
```json
{
  "idPortfolio": 1302,
  "imagePath": "",
  "name": "Jonh Doe",
  "experience": "I have experience working with java",
  "twitterUserName": "MicrosoftBR",
  "tweets": [
    {
      "idTwitter": 1658224843530043392,
      "text": "@LinkedinBrasil Com um tweet desses, não há Mãecrosoft que aguente de emoção. 🥰"
    },
    {
      "idTwitter": 1658185083201675274,
      "text": "RT @msdevbr: Se você ainda precisa de razões para se registrar no #MSBuild, o @fabiohara  te conta 3! https://t.co/GlsQG4Q4Qb https://t.co/…"
    },
    {
      "idTwitter": 1658173693615386625,
      "text": "@Lia_Olafinho Olá, Lia. Boa tarde! 🙂 \nSentimos muito por isto. Por favor, nos conte um pouco mais por mensagem priv… https://t.co/GC7nzSXeNE"
    },
    {
      "idTwitter": 1657755153980706816,
      "text": "Vocês nos chamam assim e agora estamos com uma dúvida: podemos comemorar a data de hoje nem que seja um pouquinho?… https://t.co/ImbslQl9mx"
    },
    {
      "idTwitter": 1657022825398403074,
      "text": "Mal podemos esperar para ouvir sobre as práticas recomendadas que você implementou para disseminar o aprendizado de maneira disruptiva! 🤩"
    }
  ]
}
```

**b)** To add or update an image, you must create a **POST** request at the endpoint **{server}/portfolio/{idPortfolio}** with the portfolio id of the generated portfolio, 
also add the image as form-data with key **imageFile**. The images will be stored in the given directory and the path will be stored in the database:

Request: {server}/portfolio/{idPortfolio} - POST
| Key   |      Value      |
|----------|:-------------:|
| imageFile |  **the image file** |

Response:
```json
{
  "idPortfolio": 1302,
  "imagePath": "C:\\Users\\andri\\IdeaProjects\\profile_tweets\\src\\main\\resources\\photos\\car.jpg",
  "name": "Jonh Doe",
  "experience": "I have experience working with java",
  "twitterUserName": "MicrosoftBR",
  "tweets": [
    {
      "idTwitter": 1658224843530043392,
      "text": "@LinkedinBrasil Com um tweet desses, não há Mãecrosoft que aguente de emoção. 🥰"
    },
    {
      "idTwitter": 1658185083201675274,
      "text": "RT @msdevbr: Se você ainda precisa de razões para se registrar no #MSBuild, o @fabiohara  te conta 3! https://t.co/GlsQG4Q4Qb https://t.co/…"
    },
    {
      "idTwitter": 1658173693615386625,
      "text": "@Lia_Olafinho Olá, Lia. Boa tarde! 🙂 \nSentimos muito por isto. Por favor, nos conte um pouco mais por mensagem priv… https://t.co/GC7nzSXeNE"
    },
    {
      "idTwitter": 1657755153980706816,
      "text": "Vocês nos chamam assim e agora estamos com uma dúvida: podemos comemorar a data de hoje nem que seja um pouquinho?… https://t.co/ImbslQl9mx"
    },
    {
      "idTwitter": 1657022825398403074,
      "text": "Mal podemos esperar para ouvir sobre as práticas recomendadas que você implementou para disseminar o aprendizado de maneira disruptiva! 🤩"
    }
  ]
}
```

**c)** To update a Portfolio, you must create a **PUT** request at the endpoint **{server}/portfolio/{idPortfolio}** with the portfolio id of the generated portfolio.
The only impossible changes are the Tweets themselves because it's automatically pushed from the Twitter API, however, you can change the **twitterUserName** in which the
tweets are going to be retrieved from:

Request: {server}/portfolio/{idPortfolio} - PUT
```json
{
  "imagePath": "C:\\Users\\andri\\IdeaProjects\\profile_tweets\\src\\main\\resources\\photos\\car.jpg",
  "name": "Jonh Doe",
  "experience": "I have experience working with java",
  "twitterUserName": "Twitter"
}
```
Response:
```json
{
  "idPortfolio": 1302,
  "imagePath": "C:\\Users\\andri\\IdeaProjects\\profile_tweets\\src\\main\\resources\\photos\\car.jpg",
  "name": "Jonh Doe",
  "experience": "I have experience working with java",
  "twitterUserName": "Twitter",
  "tweets": [
    {
      "idTwitter": 1657074172315717633,
      "text": "RT @Subscriptions: You asked (loudly), we listened.\n\nWe’ve reduced the signup flow for creators from 27 steps to just 4.\n\nIt’s never been e…"
    },
    {
      "idTwitter": 1656697812266909696,
      "text": "Say goodbye to prying eyes and hello to secure conversations. We're giving early access to Encrypted Direct Message… https://t.co/diSzwN83WV"
    },
    {
      "idTwitter": 1652034062788206595,
      "text": "WORLDWIDE! Creators across the globe can now sign up and earn a living on Twitter.\n\nTap on “Monetization” in settin… https://t.co/V32aqisAT4"
    },
    {
      "idTwitter": 1649507477325488131,
      "text": "Creators can now sign up and earn a living directly on Twitter in the EU, UK, and EEA.\n\nTap on “Monetization” in se… https://t.co/krlyUXDU89"
    },
    {
      "idTwitter": 1647684850223312896,
      "text": "Creators can now sign up and earn a living directly on Twitter in Japan, Canada, Australia, and New Zealand.\n\nTap o… https://t.co/SPws8Q7fha"
    }
  ]
}
```

**d)** To retrieve all the Portfolios, you must create a **GET** request at the endpoint **{server}/portfolio**

Request: {server}/portfolio - GET

Response:
```json
[
  {
    "idPortfolio": 1252,
    "imagePath": null,
    "name": null,
    "experience": "Test",
    "twitterUserName": "MicrosoftBr",
    "tweets": [
      {
        "idTwitter": 1658224843530043392,
        "text": "@LinkedinBrasil Com um tweet desses, não há Mãecrosoft que aguente de emoção. 🥰"
      },
      {
        "idTwitter": 1658185083201675274,
        "text": "RT @msdevbr: Se você ainda precisa de razões para se registrar no #MSBuild, o @fabiohara  te conta 3! https://t.co/GlsQG4Q4Qb https://t.co/…"
      },
      {
        "idTwitter": 1658173693615386625,
        "text": "@Lia_Olafinho Olá, Lia. Boa tarde! 🙂 \nSentimos muito por isto. Por favor, nos conte um pouco mais por mensagem priv… https://t.co/GC7nzSXeNE"
      },
      {
        "idTwitter": 1657755153980706816,
        "text": "Vocês nos chamam assim e agora estamos com uma dúvida: podemos comemorar a data de hoje nem que seja um pouquinho?… https://t.co/ImbslQl9mx"
      },
      {
        "idTwitter": 1657022825398403074,
        "text": "Mal podemos esperar para ouvir sobre as práticas recomendadas que você implementou para disseminar o aprendizado de maneira disruptiva! 🤩"
      }
    ]
  },
  {
    "idPortfolio": 1302,
    "imagePath": "C:\\Users\\andri\\IdeaProjects\\profile_tweets\\src\\main\\resources\\photos\\car.jpg",
    "name": "Jonh Doe",
    "experience": "I have experience working with java",
    "twitterUserName": "Twitter",
    "tweets": [
      {
        "idTwitter": 1657074172315717633,
        "text": "RT @Subscriptions: You asked (loudly), we listened.\n\nWe’ve reduced the signup flow for creators from 27 steps to just 4.\n\nIt’s never been e…"
      },
      {
        "idTwitter": 1656697812266909696,
        "text": "Say goodbye to prying eyes and hello to secure conversations. We're giving early access to Encrypted Direct Message… https://t.co/diSzwN83WV"
      },
      {
        "idTwitter": 1652034062788206595,
        "text": "WORLDWIDE! Creators across the globe can now sign up and earn a living on Twitter.\n\nTap on “Monetization” in settin… https://t.co/V32aqisAT4"
      },
      {
        "idTwitter": 1649507477325488131,
        "text": "Creators can now sign up and earn a living directly on Twitter in the EU, UK, and EEA.\n\nTap on “Monetization” in se… https://t.co/krlyUXDU89"
      },
      {
        "idTwitter": 1647684850223312896,
        "text": "Creators can now sign up and earn a living directly on Twitter in Japan, Canada, Australia, and New Zealand.\n\nTap o… https://t.co/SPws8Q7fha"
      }
    ]
  }
]
```

**e)** To get a specific Portfolio, you must create a **GET** request at the endpoint **{server}/portfolio/{idPortfolio}** with the portfolio id of the generated portfolio.

Request: {server}/portfolio/{idPortfolio} - GET

Response:
```json
{
  "idPortfolio": 1302,
  "imagePath": "C:\\Users\\andri\\IdeaProjects\\profile_tweets\\src\\main\\resources\\photos\\car.jpg",
  "name": "Jonh Doe",
  "experience": "I have experience working with java",
  "twitterUserName": "Twitter",
  "tweets": [
    {
      "idTwitter": 1657074172315717633,
      "text": "RT @Subscriptions: You asked (loudly), we listened.\n\nWe’ve reduced the signup flow for creators from 27 steps to just 4.\n\nIt’s never been e…"
    },
    {
      "idTwitter": 1656697812266909696,
      "text": "Say goodbye to prying eyes and hello to secure conversations. We're giving early access to Encrypted Direct Message… https://t.co/diSzwN83WV"
    },
    {
      "idTwitter": 1652034062788206595,
      "text": "WORLDWIDE! Creators across the globe can now sign up and earn a living on Twitter.\n\nTap on “Monetization” in settin… https://t.co/V32aqisAT4"
    },
    {
      "idTwitter": 1649507477325488131,
      "text": "Creators can now sign up and earn a living directly on Twitter in the EU, UK, and EEA.\n\nTap on “Monetization” in se… https://t.co/krlyUXDU89"
    },
    {
      "idTwitter": 1647684850223312896,
      "text": "Creators can now sign up and earn a living directly on Twitter in Japan, Canada, Australia, and New Zealand.\n\nTap o… https://t.co/SPws8Q7fha"
    }
  ]
}
```

**f)** To delete a specific Portfolio, you must create a **DELETE** request at the endpoint **{server}/portfolio/{idPortfolio}** with the portfolio id of the generated portfolio.

Request: {server}/portfolio/{idPortfolio} - DELETE

Response: OK

#### 2. Using the Front End

In order to use the front end, only start the application, open a browser, and tap **{server}/**

**a)** In order to create a portfolio select the button **PORTFOLIO CREATION** which is in the header of the page, with the **newPortfolioForm** page opened, tap the
information and then click **SAVE**. The created Portfolio will be presented on the index page right after its creation.

**b)** To update a portfolio or add an image click on the **UPDATE** button and wait for the **openUpdatePortfolioForm** page to be presented. Change any information desired
and click **SAVE** or press **Choose a file** and select an image and then **Upload** in order to add an image to your portfolio. The changes will be presented on the index page

**c)** To delete a portfolio, click the **DELETE** button, after that the portfolio will disappear. 

