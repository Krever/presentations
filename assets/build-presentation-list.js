// Sample data in JSON format
const presentationsData = [
    {
        title: "Durable Event-Sourced Workflow Monad... Seriously!",
        meta: "Scala",
        conferences: [
            {
                name: "Scalar 2025",
                link: "https://www.scalar-conf.com/",
                slides: null,
                video: null
            }
        ]
    },
    {
        title: "Creative Engineering: The Subtle Art of Cutting Corners",
        meta: "General",
        conferences: [
            {
                name: "L8Conf 2025",
                meta: "General",
                link: "https://l8conf.com/",
                slides: null,
                video: null
            }
        ]
    },
    {
        title: "Decisions4s: Complicated Conditionals, Higher-Kinded Data and Scala 3",
        meta: "Scala",
        conferences: [
            {
                name: "Art of Scala 2024",
                link: "https://artofscala.com/",
                slides: "2024-10-art-of-scala",
                video: "https://www.youtube.com/watch?v=J4ZXsR25M7k"
            },
            {
                name: "Scala.io 2024",
                link: "https://scala.io/",
                slides: "2024-11-scala-io",
                video: null
            }
        ]
    },
    {
        title: "Communication For Developers",
        meta: "General",
        conferences: [
            {
                name: "Sphere.it 2023",
                link: "https://sphere.it/event/sphere-it-conf-2023",
                slides: "2023-09-sphere-it",
                video: "https://www.youtube.com/watch?v=rXSu3Q6wFj0"
            },
            {
                name: "Voxxed Days Zurich 2024",
                link: "https://voxxeddays.com/zurich/",
                slides: "2024-03-voxxed-days-zurich",
                video: "https://www.youtube.com/watch?v=bVRbxVCHrPE&list=PLRsbF2sD7JVrJJWeSPM7WjYNO42O-aF_o&index=21"
            },
            {
                name: "Geecon 2024",
                link: "https://2024.geecon.org",
                slides: "2024-03-voxxed-days-zurich",
                video: "https://www.youtube.com/watch?v=i3T9_ntm1S4&list=PLxZQe6I1pYpet-lLqg22YWMiANXGhJxCN&index=67"
            },
            {
                name: "Dev Days Europe 2024",
                link: "https://devdays.lt",
                slides: "2024-03-voxxed-days-zurich",
                video: "https://www.youtube.com/watch?v=NLYLHZQ-Qj8&list=PLqYhGsQ9iSEpFD1hBBle_VkpJ-S-FC4Lc&index=53"
            }
        ]
    },
    {
        title: "Love, Hate And Workflows",
        meta: "Scala",
        conferences: [
            {
                name: "Functional Scala 2022",
                link: "https://www.functionalscala.com",
                slides: "2022-12-functional-scala",
                video: "https://www.youtube.com/watch?v=jLN1ibwUwuY"
            },
            {
                name: "Art of Scala 2022",
                link: "https://www.eventbrite.com/e/a-unique-conference-for-scala-engineers-and-enthusiasts-tickets-431525433187",
                slides: "2022-09-yava-conf",
                video: null
            },
            {
                name: "Functional World #5",
                link: "https://www.meetup.com/functionalworld/events/288622105/",
                slides: "2022-09-yava-conf",
                video: "https://youtu.be/S5lohF1YTl0"
            },
            {
                name: "Ya!va Conf 2022",
                link: "https://yavaconf.com/",
                slides: "2022-09-yava-conf",
                video: null
            }
        ]
    },
    {
        title: "Magic Of Integrations. Ecosystem Better Than Any Framework",
        meta: "Scala",
        conferences: [
            {
                name: "ScalaConf.ru 2019",
                link: "https://scalaconf.ru/en/2019",
                slides: "2019-11-scalaconfru",
                video: "https://www.youtube.com/watch?v=CxitC7fNTRU"
            },
        ]
    },
    {
        title: "Functional Frontend. Practical Survival Guide For Backend Engineers",
        meta: "Scala",
        conferences: [
            {
                name: "Scala.io 2019",
                link: "https://scala.io/",
                slides: "2019-10-scalaio",
                video: "https://www.youtube.com/watch?v=72GyZuStsvM"
            },
        ]
    },
    {
        title: "Pragmatic, Object-Oriented Tagless Final",
        meta: "Scala",
        conferences: [
            {
                name: "Kraków SUG 2020",
                link: "https://www.meetup.com/krakow-scala-user-group/events/268614614/",
                slides: "2020-02-krakow-sug",
                video: null
            },
            {
                name: "Scala Italy 2019",
                link: "http://2019.scala-italy.it",
                slides: "2019-09-scalaitaly",
                video: "https://vimeo.com/363249962"
            },
            {
                name: "LxScala 2019",
                link: "https://www.facebook.com/LXScala/",
                slides: "2019-09-scalaitaly",
                video: null
            },
        ]
    },
    {
        title: "That's Enough, Bash. That's Enough.",
        meta: "Scala",
        conferences: [
            {
                name: "Scalawave 2018",
                link: "https://scalawave.io",
                slides: "2018-09-scalawave",
                video: "https://youtu.be/xHdgeOco-OU"
            },
        ]
    },
    {
        title: "Object Algebras And Why You Won't Touch Http Library Ever Again",
        meta: "Scala",
        conferences: [
            {
                name: "Scalar 2018",
                link: "https://scalar-conf.com/2018/",
                slides: "2018-04-scalar",
                video: "https://youtu.be/2Jo75rgnVW0"
            },
        ]
    },
    {
        "title": "It's Just A Monoid In The Category Of Endofunctors",
        meta: "Scala",
        "conferences": [
            {
                "name": "Functional Tricity #7",
                "slides": "2017-06-functional-tricity/index.html",
                "video": "https://youtu.be/5JfddiM3uHk",
                "link": "https://www.meetup.com/functionalworld/events/239505311/"
            }
        ]
    },
    {
        "title": "Why Do We Need 9 Different Data Processing Engines? What Makes Spark 2.32 Times Better Than Flink?",
        meta: "General",
        "conferences": [
            {
                "name": "Datamass 2017",
                "slides": "2017-09-datamass/index.html",
                "video": "https://youtu.be/sMEfvnIX4Co",
                "link": "https://summit.datamass.io/"
            }
        ]
    },
    {
        "title": "[code_smarter => test_less] Functional Guide To Reasonable Development",
        meta: "General",
        "conferences": [
            {
                "name": "Norwegian IT Night Tricity 2017",
                "slides": "2017-10-norwegian-it-night-tricity/index.html",
                "video": null,
                "link": "https://norwegian-it-night-tricity.confetti.events/"
            }
        ]
    }
];

function createCards(data) {
    const container = document.getElementById("cardContainer");

    data.forEach(presentation => {
        // Card
        const columnDiv = document.createElement("div");
        columnDiv.className = "column";

        const cardDiv = document.createElement("div");
        cardDiv.className = "ui fluid card";

        // Card Content
        const cardContent = document.createElement("div");
        cardContent.className = "content";
        cardDiv.appendChild(cardContent);

        // Card Header (Presentation Title)
        const headerDiv = document.createElement("div");
        headerDiv.className = "header";
        headerDiv.textContent = presentation.title;
        cardContent.appendChild(headerDiv);

        const descContent = document.createElement("div");
        descContent.className = "content";
        cardDiv.appendChild(descContent);

        const descriptionDiv = document.createElement("div");
        descriptionDiv.className = "description";
        descContent.appendChild(descriptionDiv);

        // Card Content
        const cardExtraContent = document.createElement("div");
        cardExtraContent.className = "extra content";
        cardDiv.appendChild(cardExtraContent)

        const metaDiv = document.createElement("div")
        metaDiv.className = "ui tiny label";
        metaDiv.textContent = presentation.meta;
        cardExtraContent.appendChild(metaDiv);

        // Conference List
        const conferenceList = document.createElement("div");
        conferenceList.className = "ui list";

        presentation.conferences.forEach(conference => {
            const conferenceItem = document.createElement("div");
            conferenceItem.className = "item";

            const conferenceHeader = document.createElement("div");
            conferenceHeader.className = "header";
            conferenceItem.appendChild(conferenceHeader);

            // Conference Name
            const conferenceName = document.createElement("a");
            conferenceName.href = conference.url;
            conferenceName.className = "conference-name"
            conferenceName.textContent = conference.name;
            conferenceHeader.prepend(conferenceName);

            // Slides Icon Link
            if (conference.slides) {
                const slidesLink = document.createElement("a");
                slidesLink.href = conference.slides;
                slidesLink.target = "_blank";
                slidesLink.className = "ui right floated icon";
                slidesLink.style.marginLeft = "8px";
                slidesLink.title = "Slides";
                slidesLink.innerHTML = `<i class="file powerpoint outline icon"></i>`;
                conferenceHeader.appendChild(slidesLink);
            }

            // Video Icon Link
            if (conference.video) {
                const videoLink = document.createElement("a");
                videoLink.href = conference.video;
                videoLink.target = "_blank";
                videoLink.className = "ui right floated icon";
                videoLink.style.marginLeft = "8px";
                videoLink.title = "Video";
                videoLink.innerHTML = `<i class="video icon"></i>`;
                conferenceHeader.appendChild(videoLink);
            }

            conferenceList.appendChild(conferenceItem);
        });

        descriptionDiv.appendChild(conferenceList)
        columnDiv.appendChild(cardDiv);
        container.appendChild(columnDiv);
    });
}

// Initialize the cards with data
createCards(presentationsData);