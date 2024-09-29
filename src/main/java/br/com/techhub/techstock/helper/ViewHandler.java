package br.com.techhub.techstock.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ViewHandler {

    public String staticDir = System.getProperty("user.dir") + File.separator
        + "src/main/resources/static/website" + File.separator;

    public String getHtml(String filename) throws FileNotFoundException {
        var result = "";

        File file = new File(staticDir + filename);
        try (
            Scanner myReader = new Scanner(file)
        ) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                result += data;
            }

            return result;
        }
    }

    @GetMapping("/equipamentos")
    public ResponseEntity<String> equipamentos() {
        try {
            return ResponseEntity.ok(getHtml("equipamentos.html"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
