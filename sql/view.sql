-- Musikverein: view.sql

drop view alle_proben;

create view alle_proben as (
select
    Namen, Bezeichnung, Zeitpunkt, Raumnr, Gebaeudename
from
    Mitglied
    natural inner join spielen
    natural inner join Musikgruppe
    inner join proben on Bezeichnung = proben.MGName
    natural inner join Termin
    natural inner join Raum
    natural inner join Gebaeude
);

select
    Namen, count(Zeitpunkt)
from
    alle_proben
group by
    Namen
;
