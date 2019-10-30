package prime;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import dao.SessionDao;
import dto.Session;

@ManagedBean(name = "chartModel")
public class Chart implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private BarChartModel liveBarModel;
    private Integer max = 200;
    private SessionDao sd = new SessionDao();
    private List<Session> sessions = sd.getAllInLastDay();
    
    
    @PostConstruct
    public void init() {
        initLiveBarModel();
    }
    

    // TODO: uraditi racunanje kad je ko bio online u bazi,
    public BarChartModel getLiveBarModel() {
        
        ChartSeries engine1 = new ChartSeries();
        engine1.setLabel("Engine 1");
        SimpleDateFormat sdf = new SimpleDateFormat("HH");    
        for(int i = 0; i < 24; i++) {
        	
        	int sum = 0;
        	for(Session session : sessions) {
        		if(session.getTimeLogged() < System.currentTimeMillis() - i*3600000 && session.getTimeLogged() > System.currentTimeMillis() - (i+1)*3600000)
        			sum++;
        	}
        	        	
        	engine1.set(sdf.format(new Date(System.currentTimeMillis() - i*3600000)) + " - " + sdf.format(new Date(System.currentTimeMillis() - (i+1)*3600000)), sum);
        }
       


        liveBarModel.addSeries(engine1);
       
        liveBarModel.setTitle("Number of users per hour");
        
 
        Axis xAxis = liveBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Hours");
 
        Axis yAxis = liveBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Number of users");
        yAxis.setMin(0);
        yAxis.setMax(max);
        liveBarModel.setShowPointLabels(true);
        liveBarModel.setSeriesColors("ed1065, 9490b2, 00a1ff");
        
        return liveBarModel;
    }
 
    private BarChartModel initLiveBarModel() {
        liveBarModel = new BarChartModel();
        return liveBarModel;
    }
 
 
	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
}
