import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as io from 'socket.io-client';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private url = 'http://localhost:3000';
  private socket;

  constructor(private http: HttpClient) {
    this.http = http;
    this.socket = io(this.url);
  }

  readMessage(msg: any) {
    console.log(msg.id);
    const api: string = '/api/messages/' + msg.id;
    return this.http.put(api, JSON.stringify(msg));
  }

  getAllMessages() {
    return this.http.get('/api/messages');
  }

  public isUnread = () => {
    return Observable.create((observer) => {
      this.socket.on('unread', (res) => {
          observer.next(res.unread);
      });
  });
    // return this.http.get('/api/messages/available/unread');
  }
}
