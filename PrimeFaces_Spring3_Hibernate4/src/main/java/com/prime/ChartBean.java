package com.prime;


import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@RequestScoped
public class ChartBean implements Serializable {

    private CartesianChartModel categoryModel;

    private PieChartModel pieModel;

	public ChartBean() {
        createCategoryModel();
        createPieModel();
	}

	public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

    public CartesianChartModel getCategoryModel() {
        return categoryModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    private void createCategoryModel() {
        categoryModel = new CartesianChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Landline Revenue (in $Millions)");

        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        ChartSeries girls = new ChartSeries();        
        girls.setLabel("Internet Revenue (in $Millions)");

        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);

        categoryModel.addSeries(boys);
        categoryModel.addSeries(girls);
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        

        pieModel.set("Optimum Landline", 540);
        pieModel.set("Optimum Speed", 325);
        pieModel.set("OPtimumX 3", 702);
        pieModel.set("Optimum +", 421);
    }
}
                    
