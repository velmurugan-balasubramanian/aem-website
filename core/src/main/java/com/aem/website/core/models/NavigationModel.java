package com.aem.website.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.List;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.settings.SlingSettingsService;

@Model(
	    adaptables = {Resource.class},
	    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public interface NavigationModel {
	
	@Inject
	  List<MenuItem> getMenuItem(); // the name `getCompanies` corresponds to the multifield name="./companies"
	  /**
	   * Company model
	   * has a name and a list of departments
	   */
	  @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
	  interface MenuItem {
	    @Inject
	    String getMenuText();
	    
	    @Inject
	    String getMenuLink();
	    
	    @Inject
	    List<SubMenuItem> getSubmenuItem(); // the name `getDepartments` corresponds to the multifield name="./departments"
	  }
	  /**
	   * Department model
	   * has a name and a manager
	   */
	  @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
	  interface SubMenuItem {
		    @Inject
		    String getSubmenuText();
		    
		    @Inject
		    String getSubmenuLink();
	  }
}
