package com.brg.mock;

import org.apache.ddlutils.model.Column;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;

public class TargetDatabaseMock {
    private Database database;
    private Table afdelingen, cursussen, historie, inschrijvingen, medewerkers, schalen, uitvoering;

    public TargetDatabaseMock() {
        database = new Database();
        afdelingen = new Table();
        cursussen = new Table();
        historie = new Table();
        inschrijvingen = new Table();
        medewerkers = new Table();
        schalen = new Table();
        uitvoering = new Table();

        createColumns();
        setTableNames();
    }

    public void setTableNames() {
        afdelingen.setName("AFDELINGEN");
        cursussen.setName("CURSUSSEN");
        historie.setName("HISTORIE");
        inschrijvingen.setName("INSCHRIJVINGEN");
        medewerkers.setName("MEDEWERKERS");
        schalen.setName("SCHALEN");
        uitvoering.setName("UITVOERINGEN");

        database.addTable(afdelingen);
        database.addTable(cursussen);
        database.addTable(historie);
        database.addTable(inschrijvingen);
        database.addTable(medewerkers);
        database.addTable(schalen);
        database.addTable(uitvoering);

    }

    public void createColumns() {
        afdelingColumns();
        cursussenColumns();
        historieColumns();
        inschrijvingenColumns();
        medewerkersColumns();
        schalenColumns();
        uitvoeringenColumns();
    }

    private void afdelingColumns() {
        Column anr, naam, locatie, hoofd;
        anr = new Column();
        naam = new Column();
        locatie = new Column();
        hoofd = new Column();

        anr.setName("ANR");
        naam.setName("NAAM");
        locatie.setName("LOCATIE");
        hoofd.setName("HOOFD");

        afdelingen.addColumn(anr);
        afdelingen.addColumn(naam);
        afdelingen.addColumn(locatie);
        afdelingen.addColumn(hoofd);
    }

    private void cursussenColumns() {
        Column code, omschrijving, type, lengthe;
        code = new Column();
        omschrijving = new Column();
        type = new Column();
        lengthe = new Column();

        code.setName("CODE");
        omschrijving.setName("OMSCHRIJVING");
        type.setName("TYPE");
        lengthe.setName("LENGTE");

        cursussen.addColumn(code);
        cursussen.addColumn(omschrijving);
        cursussen.addColumn(type);
        cursussen.addColumn(lengthe);
    }

    private void historieColumns() {
        Column mnr, begindatum, beginjaar, einddatum, afd, maandsal, opmerking, gebeurtenis;
        mnr = new Column();
        begindatum = new Column();
        beginjaar = new Column();
        einddatum = new Column();
        afd = new Column();
        maandsal = new Column();
        opmerking = new Column();
        gebeurtenis = new Column();

        mnr.setName("MNR");
        begindatum.setName("BEGINDATUM");
        beginjaar.setName("BEGINJAAR");
        einddatum.setName("EINDDATUM");
        afd.setName("AFD");
        maandsal.setName("MAANDSAL");
        opmerking.setName("OPMERKINGEN");
        gebeurtenis.setName("GEBEURTENIS");

        historie.addColumn(mnr);
        historie.addColumn(begindatum);
        historie.addColumn(einddatum);
        historie.addColumn(afd);
        historie.addColumn(maandsal);
        historie.addColumn(opmerking);
        historie.addColumn(gebeurtenis);
    }

    private void inschrijvingenColumns() {
        Column cursist, cursus, begindatum, evaluatie;

        cursist = new Column();
        cursus = new Column();
        begindatum = new Column();
        evaluatie = new Column();

        cursist.setName("CURSIST");
        cursus.setName("CURSUS");
        begindatum.setName("BEGINDATUM");
        evaluatie.setName("EVALUATIE");

        inschrijvingen.addColumn(cursist);
        inschrijvingen.addColumn(cursus);
        inschrijvingen.addColumn(begindatum);
        inschrijvingen.addColumn(evaluatie);
    }

    private void medewerkersColumns() {
        Column mnr, naam, voorl, functie, chef, gbdatum, maandsal, comm, afd;
        mnr = new Column();
        naam = new Column();
        voorl = new Column();
        functie = new Column();
        chef = new Column();
        gbdatum = new Column();
        maandsal = new Column();
        comm = new Column();
        afd = new Column();

        mnr.setName("MNR");
        naam.setName("NAAM");
        voorl.setName("VOORL");
        functie.setName("FUNCTIE");
        chef.setName("CHEF");
        gbdatum.setName("GBDATUM");
        maandsal.setName("MAANDSAL");
        comm.setName("COMM");
        afd.setName("AFD");

        medewerkers.addColumn(mnr);
        medewerkers.addColumn(naam);
        medewerkers.addColumn(voorl);
        medewerkers.addColumn(functie);
        medewerkers.addColumn(chef);
        medewerkers.addColumn(gbdatum);
        medewerkers.addColumn(maandsal);
        medewerkers.addColumn(comm);
        medewerkers.addColumn(afd);
    }

    private void schalenColumns() {
        Column snr, ondergrens, bovengrens, toelage;
        snr = new Column();
        ondergrens = new Column();
        bovengrens = new Column();
        toelage = new Column();

        snr.setName("SNR");
        ondergrens.setName("ONDERGRENS");
        bovengrens.setName("BOVENGRENS");
        toelage.setName("TOELAGE");

        schalen.addColumn(snr);
        schalen.addColumn(ondergrens);
        schalen.addColumn(bovengrens);
        schalen.addColumn(toelage);
    }

    private void uitvoeringenColumns() {
        Column cursus, begindatum, docent, locatie;
        cursus = new Column();
        begindatum = new Column();
        docent = new Column();
        locatie = new Column();

        cursus.setName("CURSUS");
        begindatum.setName("BEGINDATUM");
        docent.setName("DOCENT");
        locatie.setName("LOCATIE");

        uitvoering.addColumn(cursus);
        uitvoering.addColumn(begindatum);
        uitvoering.addColumn(docent);
        uitvoering.addColumn(locatie);
    }

    public Database getDatabase() {
        return database;
    }
}
