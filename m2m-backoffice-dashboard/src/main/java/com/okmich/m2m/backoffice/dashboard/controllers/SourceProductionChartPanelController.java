/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.okmich.m2m.backoffice.dashboard.controllers;

import static com.okmich.m2m.backoffice.dashboard.OptionRegistry.*;
import com.okmich.m2m.backoffice.dashboard.model.Sensor;
import com.okmich.m2m.backoffice.dashboard.views.UIView;

/**
 *
 * @author ABME340
 */
public class SourceProductionChartPanelController extends AbstractController<Sensor> {

    /**
     * SOURCE_SENSOR
     */
    private final static String SOURCE_SENSOR = value(SOURCE_SENSOR_ID);

    /**
     *
     * @param view
     */
    public SourceProductionChartPanelController(UIView view) {
        super(view);
    }

    @Override
    public void process(String t) {
        //devId;ts;prs;tmp;vol;flv;xbf
        String[] fields = t.split(";");
        long ts = Long.parseLong(fields[1]);
        double vol = Double.parseDouble(fields[4]);

        double totaldailyVol = 20;
        Sensor sensor = new Sensor();
        sensor.setCapacity(totaldailyVol);
        if (SOURCE_SENSOR.equals(fields[0])) {
            //do some load
            perform(sensor);
        }
    }

    @Override
    public void perform(Sensor t) {
        if (!SOURCE_SENSOR.equals(t.getDevId())) {
            return;
        }
        //do nothing
        if (Sensor.STATUS_ACTIVE.equals(t.getStatus())) {
            long ts = t.getTimestamp();
            double vol = t.getCapacity();

            //do some logic
            double totaldailyVol = 20;
            t.setCapacity(totaldailyVol);
            this.uiView.refreshData(t);
        }
    }

    @Override
    protected Sensor transformPayload(String payload) {
        return null;
    }

}
