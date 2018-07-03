import { Component } from '@angular/core';
import { MessageService } from './service/message.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss', '../assets/css/custom.css', '../assets/css/style.lightblue.css']
})

export class AppComponent {
  title = 'Notification Application';

  constructor(messageService: MessageService) { }
}
