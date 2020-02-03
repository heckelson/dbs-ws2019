-- Musikverein: trigger_test.sql

insert into Mitglied (Namen, Geburtsdatum, Beitrittsdatum)
    values('George Orwell', '21-JAN-1950', '22-JAN-1984');
    
insert into Mitglied (Namen, Geburtsdatum, Beitrittsdatum)
    values('Mary Shelly', '30-AUG-1797', '21-SEP-2008');

insert into Mitglied (Namen, Geburtsdatum, Beitrittsdatum)
    values('Mary Shelly', '30-AUG-1797', '21-SEP-2008');
    
select Mitgliedsnummer, Namen, Geburtsdatum from Mitglied;
