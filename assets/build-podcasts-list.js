// Sample data in JSON format
const podcastsData = [
    {
        title: "Modelling the Business Domain",
        description: "Scala Space",
        meta: "Nov 22, 2024",
        link: "https://www.youtube.com/watch?v=-8k3WfXVHkc&t=291s",
    },
    {
        title: "Discover SwissBorg",
        description: "Scala for Fun & Profit",
        meta: "Mar 5, 2024",
        link: "https://www.youtube.com/watch?v=NfU0NJRvrfE",
    },
];

import {html, render} from 'https://cdn.jsdelivr.net/npm/lit-html@3.2.1';

const createCard = (podcast) => html`
    <div class="column">
        <div class="ui fluid card">
            <div class="content">
                <div class="header">${podcast.title}
                    <a href="${podcast.link}" target="_blank" class="ui right floated icon" title="Video">
                        <i class="video icon"></i>
                    </a>
                </div>
            </div>
            <div class="content">
                <div class="description">
                    <span class="conference-name">
                        ${podcast.description}
                        <span class="right floated">${podcast.meta}</span>
                    </span>
                </div>
            </div>
        </div>
    </div>
`;

const renderCards = () => {
    const cardsTemplate = html`
        ${podcastsData.map(podcast => createCard(podcast))}
    `;
    render(cardsTemplate, document.getElementById('podcastsContainer'));
};

renderCards();
