package com.example.market.controller;

import com.example.market.entity.board.Board;
import com.example.market.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class imageController {
    private final BoardService boardService;

    @GetMapping(value="/image/{boardId}", produces= MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable Long boardId)
            throws IOException {
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Board board = boardService.findBoard(boardId);
        String fileDir = board.getFilepath(); // 파일경로

        try{
            fis = new FileInputStream(fileDir);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        int readCount = 0;
        byte[] buffer = new byte[1024];
        byte[] fileArray = null;

        try{
            while((readCount = fis.read(buffer)) != -1){
                baos.write(buffer, 0, readCount);
            }
            fileArray = baos.toByteArray();
            fis.close();
            baos.close();
        } catch(IOException e){
            throw new RuntimeException("File Error");
        }
        return fileArray;
    }
}
