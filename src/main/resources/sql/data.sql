INSERT INTO application_user(username, password, role)
VALUES ('anna', '$2a$10$5YVfAzYw2gz0To69fzv88ui8iZL/IV7iwZBI33LPAf.kzWT80ZXMi', 1),/*admin*/
       ('alex', '$2a$10$yzyNFbmVkwPg3T1gDFZ63.ZZVJI3odPMS1dhBXXuJYTCqZd/XGLRW', 1),/*admin*/
       ('walter', '$2a$10$fOx52CIUnboMJkZMimdwFOasaLb.zffBZe/WjhyTD0zehRM9ia3wi', 0); /*user*/

INSERT INTO channel(name, date, subscribers)
VALUES
    ('Beyond Fireship', '2022-09-04', '329000'),
    ('Tech Acad', '2017-03-28', '75000'),
    ('Coding Wizards', '2023-01-15', '480000'),
    ('Data Science Insights', '2020-08-20', '105000'),
    ('Game Dev Hub', '2018-12-10', '210000'),
    ('AI Explorers', '2021-06-28', '145000');

INSERT INTO video(title, views, link, genre)
VALUES
    ('Next.js 13-The Basics', '512000', 'https://youtu.be/__mSgDEOyv8?si=PzO5bV1nShiWz30p', 'Educational'),
    ('Time is Relative, even in JS', '96000', 'https://youtu.be/acemrBKuDqw?si=0pDWXFSWkD6oMq0A', 'Educational'),
    ('Pythonic Data Structures', '320000', 'https://youtu.be/ajk-BrRzytA?si=4N83cKWGTmSdowrC', 'Educational'),
    ('Unity Game Development Tutorial', '210000', 'https://youtu.be/ce9wGz_LoQ0?si=kffFZyVaRryBrRzx', 'Horror'),
    ('Machine Learning Fundamentals', '180000', 'https://youtu.be/9sHpEgH1nrM?si=dYiY9JbkTtqB9Lmm', 'Educational'),
    ('Responsive Web Design with CSS Grid', '220000', 'https://youtu.be/KBmr0xG4kEk?si=fzB3RE1VSm6BQYrl', 'Horror');

INSERT INTO comment(text, likes)
VALUES
    ('Very interesting!', '2003'),
    ('Love it!', '93'),
    ('Great explanation!', '857'),
    ('Awesome content!', '427'),
    ('Helpful tutorial, thank you!', '209'),
    ('I learned a lot from this!', '118');

-- Connect videos with channels
INSERT INTO channel_video (channel_id, video_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 5),
    (4, 6);