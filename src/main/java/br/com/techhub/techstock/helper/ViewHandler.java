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

    private String staticDir = System.getProperty("user.dir") + File.separator
        + "src/main/resources/static/website" + File.separator;

    private String getHtml(String filename) throws FileNotFoundException {
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

    private ResponseEntity<String> handleViewRequest(String filename) {
        try {
            return ResponseEntity.ok(getHtml(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<String> index() {
        return handleViewRequest("index.html");
    }
}
