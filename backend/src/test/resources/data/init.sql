insert into name (
    id,
    birth_year,
    death_year,
    nconst,
    primary_name)
values (
    1,
    null,
    null,
    'nm0621448',
    'Pappan Naripatta')
;

insert into name (
    id,
    birth_year,
    death_year,
    nconst,
    primary_name)
values (
    2,
    null,
    null,
    'nm0621456',
    'Mikio Narita')
;

insert into title (
    id,
    end_year,
    is_adult,
    original_title,
    primary_title,
    runtime_minutes,
    start_year,
    tconst,
    title_type)
values (
    1,
    null,
    true,
    'Carmencita',
    'Carmencita',
    4,
    '1894',
    'tt0000001',
    'movie')
;

insert into principal (
    id,
    category,
    characters,
    job,
    nconst,
    ordering,
    tconst)
values (
    1,
    'director',
    '["Self"]',
    'director of photography',
    'nm0621448',
    1,
    'tt0000001')
;

insert into principal (
    id,
    category,
    characters,
    job,
    nconst,
    ordering,
    tconst)
values (
    2,
    'producer',
    null,
    'producer',
    'nm0621456',
    2,
    'tt0000001')
;

insert into title_genres (
    title_id,
    genres)
values (
    1,
    'Action')
;

