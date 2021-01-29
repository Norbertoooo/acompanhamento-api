package com.acompanhamento.api.web;

import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

@RestController
@RequestMapping("/api/fichas")
@CrossOrigin(origins = "*")
@Log4j2
public class FichaResource {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/exportar/{id_paciente}")
    public void exportarFicha(HttpServletResponse response, @PathVariable String id_paciente) throws IOException, JRException {

        // TODO: 29/01/2021 transformar em service
        // Pega o arquivo .jrxml localizado em resources/relatorios
        // Existe diversas formas de conseguir o caminho do arquivo
        String caminho = resourceLoader.getResource("classpath:relatorios/ficha.jrxml").getURI().getPath();

        // Compila o arquivo jrxml, caso use arquivo .jasper não precisa dessa linha pois .jasper já é o relatório compilado
        JasperReport jasperReport = JasperCompileManager.compileReport(caminho);

        // Passa para o JasperPrint o relatório, os parâmetros e a fonte dos dados, no caso uma conexão ao banco de dados
        // Nessa linha está sendo passado parametros e conexão com banco nulos
        // Caso relatorio esteja em branco, é a conexão com banco
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), new JREmptyDataSource());

        // Configura a resposta para o tipo PDF
        response.setContentType("application/pdf");
        // Define que o arquivo pode ser visualizado no navegador e também nome final do arquivo
        // para fazer download do relatório troque 'inline' por 'attachment'
        response.setHeader("Content-Disposition", "inline; filename=ficha.pdf");

        // Faz a exportação do relatório para o HttpServletResponse
        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }

}
