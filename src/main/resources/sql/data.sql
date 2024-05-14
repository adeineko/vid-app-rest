INSERT INTO application_user(username, password, role)
VALUES ('anna', '$2a$10$5YVfAzYw2gz0To69fzv88ui8iZL/IV7iwZBI33LPAf.kzWT80ZXMi', 1),
       ('alex', '$2a$10$yzyNFbmVkwPg3T1gDFZ63.ZZVJI3odPMS1dhBXXuJYTCqZd/XGLRW', 1),
       ('walter', '$2a$10$fOx52CIUnboMJkZMimdwFOasaLb.zffBZe/WjhyTD0zehRM9ia3wi', 0);

INSERT INTO channel(name, date, subscribers)
VALUES ('Beyond Fireship', '2022-09-04', '329000'),
       ('Tech Acad', '2017-03-28', '75000');
INSERT INTO video(title, views, link, genre)
VALUES ('Next.js 13-The Basics', '512000', 'https://youtu.be/__mSgDEOyv8?si=PzO5bV1nShiWz30p', 'Educational'),
       ('Time is Relative, even in JS', '96000', 'https://youtu.be/acemrBKuDqw?si=0pDWXFSWkD6oMq0A', 'Educational');
INSERT INTO comment(text, likes)
VALUES ('Very interesting!', '2003'),
       ('love it!', '93');

INSERT INTO channel_video (channel_id, video_id)
VALUES (1, 1);
INSERT INTO channel_video (channel_id, video_id)
VALUES (1, 2)