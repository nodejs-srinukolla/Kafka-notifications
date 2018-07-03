import { Component, OnInit } from '@angular/core';
import { MessageService } from '../service/message.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  public messages;
  public msgUnread: any = false;

  constructor(private messageService: MessageService) { }

  ngOnInit() {
    this.isUnread();
  }

  getAllMessages(event) {
    console.log('event=' + event);
    this.messageService.getAllMessages().subscribe(
      data => { this.messages = data; },
      (err) => console.log('Could not get All Messages from server, reason: ', err),
      () => console.log('Sent Message GET call to findAll to server.'),
    );
  }

  isUnread() {
    this.messageService.isUnread().subscribe(
      data => {
        console.log('data=' + data);
        this.msgUnread = data;
      },
      (err) => console.log('Could not get new Messages from server, reason: ', err),
      () => console.log('Sent Message Availability call to server.'),
    );
  }
}
