/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnarlitronic.wa.tabtracker.service;

import gnarlitronic.wa.tabtracker.dao.TabDao;
import gnarlitronic.wa.tabtracker.dto.Tab;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Elnic
 */
public class TabServiceImpl implements TabService {

    
    TabDao tDao;
    
    public TabServiceImpl(TabDao tDao) {
        this.tDao = tDao;
    }
    
    @Override
    public List<Tab> loadTabs() {
        return tDao.loadTabs();
    }

    @Override
    public Tab addTab(Tab t) {
        
        return tDao.addTab(t);
    
    }

    @Override
    public List<Tab> searchTabs(String searchTerm, String category) {
        return tDao.searchTabs(searchTerm, category);
    }

    @Override
    public void deleteTab(String parameter) {
        tDao.deleteTab(parameter);
    }
    
    
    
}
