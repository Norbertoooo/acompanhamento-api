package com.acompanhamento.api.service.impl;

import com.acompanhamento.api.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public byte[] gerarRelatorio(String nomeRelatorio) throws SQLException, FileNotFoundException, JRException {
        Connection connection = jdbcTemplate.getDataSource().getConnection();
        String caminho = ResourceUtils.getFile("classpath:Ficha.jrxml").getAbsolutePath();
        JasperPrint jasperPrint = JasperFillManager.fillReport(caminho, null, connection);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

}
